package Test.Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Piyush on 28/03/2017.
 */
public class HomePage extends BaseMain {


    @FindBy(how = How.CLASS_NAME, using = "ico-register")
    public WebElement register_link;


    public HomePage() {

        PageFactory.initElements(driver, this);

    }


    public void meth_goto_registerpage()
    {
        register_link.click();
    }
}
