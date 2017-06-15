@echo on
set "JAVA_EXE=%cd%\jre8\bin\javaw.exe"
start "tomcat" "%JAVA_EXE%" -Xms64m -Xmx1024m -XX:PermSize=64m -XX:MaxPermSize=256m -Djava.ext.dirs="./lib" cn.how2j.lazycat.starter.GUIStart