@echo off
echo Launching Selenium Grid hub......
appium -a 127.0.0.1 -p 4723 -bp 4724   --selendroid-port 8080  --session-override --nodeconfig nodeconfig_ios.json