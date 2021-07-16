
call cd compile_libs
call start.bat
call cd ../

call cd ant_compile
call start.bat
call cd ../

call cd maven_compile
call cd app
call start.bat
call cd ../../
