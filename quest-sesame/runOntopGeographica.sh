#!/bin/bash

PKG="systemsundertest"
RUNTIME="/home/constant/spatial-ontop-git/ontop-spatial/quest-sesame/target"
CLASS="RunOntopSpatial"
#CLASS="QuestSesameCMD"
LOGPATH="/home/constant/geographica/statistics"
OWL="/home/constant/geographica/ontology/books.owl"
MAP="/home/constant/geographica/mappings/geographica-real.obda"
EXP="MicroJoins"
REP="1"
MLIM="40"
OUTFILE=${LOGPATH}"/log-"${EXP}".txt"
ERRORFILE=${LOGPATH}"/error-"${EXP}".txt"
LOG4J=/home/constant/spatial-ontop-git/ontop-spatial/quest-sesame/src/main/resources/log4j.properties

echo ${EXP}

cd ${RUNTIME} && java -cp $(for file in `ls -1 *.jar`; do myVar=$myVar./$file":"; done;echo $myVar;) ${PKG}.${CLASS} run -owl ${OWL} -map ${MAP} -l ${LOGPATH} -r ${REP}  ${EXP}  > ${OUTFILE} 2> ${ERRORFILE}
 -Dlog4j.configuration=/home/constant/spatial-ontop-git/ontop/quest-sesame/src/main/resources/log4j.properties

#2> ${ERRORFILE}

 #2 > ${ERRORFILE}
#java -cp $(for file in `ls -1 *.jar`; do myVar=$myVar./$file":"; done;echo $myVar;)  systemsundertest.RunOntopSpatial run -owl /home/constant/books.owl -map /home/constant/mappings-ontop/geographica-real.obda-l /home/constant/geographica/statistics -r 1 -m 30 MicroSelections > log.txt 2 > error.txt
#/home/constant/spatial-ontop-git/ontop/quest-sesame/src/main/resources/log4j.properties

#EXP="MicroJoins"
#OUTFILE=${LOGPATH}"/log-"${EXP}".txt"
#echo "executing experiments: Microselections"

# java -cp $(for file in `ls -1 *.jar`; do myVar=$myVar./$file":"; done;echo $myVar;) ${PKG}.${CLASS} run -owl ${OWL} -map ${MAP} -l ${LOGPATH} -r ${REP} -m ${MLIM} ${EXP}  > ${OUTFILE}
#\ -Dlog4j.configuration=/home/constant/spatial-ontop-git/ontop/quest-sesame/src/main/resources/log4j.properties
