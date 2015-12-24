
cd src/main/webapp
docker run -d -p 80:80 -v `pwd`:/www fnichol/uhttpd