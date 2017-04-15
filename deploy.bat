@echo off
@echo
type help.txt
@echo

cmd /C mvn -DskipTests %* install

if %errorlevel% EQU 0 (

	@echo off
	IF DEFINED FBIT_DIGITALGOV_LIBS_DEPLOY_DIR (
      for /f "tokens=* delims=" %%x in (versio.txt) do set FBIT_DIGITALGOV_LIBS_VERSIO=%%x
	  @echo on
	  echo --------- COPIANT JAR %FBIT_DIGITALGOV_LIBS_VERSIO% ---------

	  xcopy /Y utils\target\fbit-digitalgov-libs.jar %FBIT_DIGITALGOV_LIBS_DEPLOY_DIR%

	) ELSE (
	  echo  =================================================================
	  echo    Definex la variable d'entorn FBIT_DIGITALGOV_LIBS_DEPLOY_DIR apuntant al
	  echo    directori de lib del projecte  i automaticament s'hi copiara
	  echo    el jar generat.
	  echo  =================================================================
	)

)
