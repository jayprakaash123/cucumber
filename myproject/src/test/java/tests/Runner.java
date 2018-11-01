package tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features= {"D:\\selenium2018\\myproject\\src\\test\\resources\\mypack\\featurefile.feature",
		"D:\\selenium2018\\myproject\\src\\test\\resources\\mypack\\featurefile2.feature"},
  
	plugin= {"pretty","html:target"},
	monochrome=true,
	
	dryRun=false)
public class Runner
{

}
