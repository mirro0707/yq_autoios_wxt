@echo off
echo 启动appium server中，请不要关闭此窗口。
appium -a 127.0.0.1 -p 4725 -bp 4726   --selendroid-port 8091 --session-override --nodeconfig nodeconfig_android1.json
appium -a 127.0.0.1 -p 4727 -bp 4728   --selendroid-port 8092 --session-override --nodeconfig nodeconfig_android2.json