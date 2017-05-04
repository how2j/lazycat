@echo on
set "JAVA_EXE=%cd%\jre8\bin\javaw.exe"
start "tomcat" "%JAVA_EXE%" -Djava.ext.dirs="./lib" cn.how2j.lazycat.starter.GUIStart