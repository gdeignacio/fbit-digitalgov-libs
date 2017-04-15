#!/usr/bin/env bash

cat help.txt

env mvn $@ -DskipTests install

if [ $? == 0 ]; then
  if [ "$FBIT_DIGITALGOV_LIBS_DEPLOY_DIR" == "" ];  then
    echo  =================================================================
    echo    Definex la variable d\'entorn FBIT_DIGITALGOV_LIBS_DEPLOY_DIR apuntant al
    echo    directori li del projecte  i automaticament s\'hi copiara
    echo    el jar generat.
    echo  =================================================================
  else
    if [ -f 'versio.txt' ]; then
	echo --------- COPIANT JAR `cat versio.txt` ---------
    else
	echo --------- COPIANT JAR ---------
    fi
    if [ -f './utils/target/fbit-digitalgov-libs.jar' ]; then
      cp ./utils/target/fbit-digitalgov-libs.jar $FBIT_DIGITALGOV_LIBS_DEPLOY_DIR
    else
      echo NO S\'HA TROBAT fbit-digitalgov-libs.jar!
    fi
  fi
fi
