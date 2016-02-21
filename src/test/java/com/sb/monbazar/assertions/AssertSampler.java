package com.sb.monbazar.assertions;

import org.hamcrest.Matcher;
import org.junit.Assert;

import static org.hamcrest.Matchers.*;

/**
 * <p>This class provides utilities to check the result of some asynchronous processes, by sampling
 * an assertion until it becomes true.</p>
 *
 * <p>"Listening for events is the fastest and most reliable way to synchronize your
 * tests with an asynchronous system.
 * [...]
 * When it isn’t possible to listen to events from the system, the next best option
 * is to repeatedly poll the system for the state change you’re expecting. If it
 * doesn’t appear within a certain timeout, you give up and fail the test."
 * (The Cucumber for Java Book - Rose, Wynne & Hellesoy - 2015)</p>
 *
 * Example :
 * <pre>
 *     import static org.hamcrest.Matchers.*;
 *     [...]
 *     AssertSampler.pollUntilResultOf(new Sample<String>() {
 *         public String execute() {
 *             return service.getState();
 *         }
 *     }, is(expected));
 * </pre>
 *
 * @author sboursault on 20/02/16.
 */
public class AssertSampler {

    private static int testVar = 0;

    public static final long DEFAULT_TIMEOUT_MILLISECS = 5000;
    public static final long DEFAULT_POLLING_INTERVAL_MILLISECS = 400;

    public static <T> void pollUntilResultOf(Sample<T> callable, Matcher<T> matcher) {
        pollUntilResultOf(callable, matcher, DEFAULT_TIMEOUT_MILLISECS);
    }

    public static <T> void pollUntilResultOf(Sample<T> callable, Matcher<T> matcher,
                                             long timeoutMillisecs) {
        pollUntilResultOf(callable, matcher, timeoutMillisecs, DEFAULT_POLLING_INTERVAL_MILLISECS);
    }

    public static <T> void pollUntilResultOf(Sample<T> callable, Matcher<T> matcher,
                                             long timeoutMillisecs, long pollIntervalMillisecs) {

        AssertionError error;
        boolean testFails;

        do {
            error = null;

            try {
                Thread.sleep(pollIntervalMillisecs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timeoutMillisecs -= pollIntervalMillisecs;

            try {
                Assert.assertThat(callable.execute(), matcher);
            } catch (AssertionError assertionError) {
                error = assertionError;
            }

            testFails = error != null;

        } while (testFails && timeoutMillisecs > 0);

        if (testFails) {
            throw error;
        }
    }


    public static void main(String[] args) {
        pollUntilResultOf(new Sample<Integer>() {
            @Override
            public Integer execute() {
                return 2;
            }
        }, is(2));
        pollUntilResultOf(new Sample<Integer>() {
            @Override
            public Integer execute() {
                return testVar++;
            }
        }, is(3));
        pollUntilResultOf(new Sample<Integer>() {
            @Override
            public Integer execute() {
                return 2;
            }
        }, is(7));
    }

    public interface Sample<T> {
        T execute();
    }
}
