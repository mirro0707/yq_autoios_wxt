package com.wxt.source;

import io.appium.java_client.ios.IOSDriver;

import java.util.function.Function;

public interface ExpectedCondition<T> extends Function<IOSDriver, T> {}