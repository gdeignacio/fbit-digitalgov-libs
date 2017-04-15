
FBIT_DIGITALGOV_LIBS_VERSIO=`cat versio.txt`

echo ]]$FBIT_DIGITALGOV_LIBS_VERSIO[[

zip -r fbit-digitalgov-libs$1-$FBIT_DIGITALGOV_LIBS_VERSIO.zip scripts versio.txt utils/target/fbit-digitalgov-libs-utils.jar  -x "**/.git**" -x "**/.svn**"  -x "scripts/templates/**" -x "scripts/pom.xml"
