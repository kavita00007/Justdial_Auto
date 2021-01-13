package com.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features="classpath:features",
		glue="com.stepsdefs",
		tags="@m",
		plugin = {"pretty",
				"html:target/html/htmlreport.html",
				"json:target/json/file.json",
				},
		publish=true,
		dryRun=false
		)

public class JustdialTestClass {

}
                  