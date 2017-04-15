
for /f "tokens=* delims=" %%x in (versio.txt) do set FBIT_DIGITALGOV_LIBS_VERSIO=%%x

zip -r fbit-digitalgov-libs%1-%FBIT_DIGITALGOV_LIBS_VERSIO%.zip scripts versio.txt utils/target/fbit-digitalgov-libs-utils.jar  -x "**/.git**" -x "**/.svn**"  -x "./scripts/pom.xml" -x "./scripts/templates/**"  -x "./scripts/templates/"
