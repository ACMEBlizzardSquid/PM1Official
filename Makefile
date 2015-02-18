# This is a simple makefile
# It allows you to create the war file for each individual project
#
# to use it 'make' and 'make clean'
#

CP=PM1Official/src/java/
CL_FOLDER=build/WEB-INF/classes

all: WSDLRetriever
	mkdir -p ${CL_FOLDER} build/WEB-INF/lib

MarfcatUtils:
	javac -cp ${CP} -d ${CL_FOLDER} PM1Official/sr/java/utils/marfcat/*.java PM1Official/sr/java/utils/io/*.java
	jar cvf MarfcatUtils.jar -C ${CL_FOLDER} .
	rm -rf ${CL_FOLDER}/*

RexCrawler:
	javac -cp ${CP} -d ${CL_FOLDER} PM1Official/src/java/utils/parser/*.java
	jar cvf RexCrawler.jar -C ${CL_FOLDER} .
	rm -rf ${CL_FOLDER}/*

WSDLRetriever: RexCrawler
	javac -cp ${CP} -d ${CL_FOLDER} PM1Official/src/java/soen487/retriever/**/*.java
	cp RexCrawler.jar build/WEB-INF/lib
	jar cvf WSDLRetriever.war -C build .
	rm -rf ${CL_FOLDER}/*

WSCAT: WSDLRetriverClient
	echo "You need the WDSL" 

WSDLRetrieverClient:
	echo "You need the WSDL"
	# first deploy the WDSLRetriever
	# get the WSDL address
	# then:
	# wsimport -clientjar WDSLRetriever.jar <wdsl>
	# rm -rf soen487


clean:
	rm -rf build RexCrawler.jar WSDLRetriever.war WSDLRetriever.jar
