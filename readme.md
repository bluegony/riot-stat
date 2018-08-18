
#build
gradle clean build

#run
java -jar build/libs/bmriot.jar bmJob connectBm
java -jar build/libs/bmriot.jar riotStatJob readRiot

