package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TodoMvcPage {

	private WebDriver driver;

	private By addToDo = By.xpath("//input[starts-with(@class,'new-todo')]");

	private By listItems = By.xpath("//ul//li//label");
	
	private By editItem = By.xpath("//input[@class='edit']");
	
	private By clearComplete = By.className("clear-completed");

	public TodoMvcPage(WebDriver driver) {

		this.driver = driver;

	}

	public String getTodoMvcPageTitle() {
		return driver.getTitle();
	}

	public void addTodoItem(String toDoItem) {


		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(addToDo));
		wait.until(ExpectedConditions.elementToBeClickable(addToDo)).sendKeys(toDoItem);

		driver.findElement(addToDo).sendKeys(Keys.ENTER);

	}

	public List<String> getTodoItems() {

		List<WebElement> todoItemsWebElement = new ArrayList<>();
		List<String> todoItems = new ArrayList<>();

		todoItemsWebElement = driver.findElements(listItems);
		for (WebElement webElement : todoItemsWebElement) {			
			todoItems.add(webElement.getText());
		}

		return todoItems;
	}

	public void updateToDoItem(String oldItem, String newItem) {
		
		Actions action = new Actions(driver);
		
		List<WebElement> todoItemsWebElement = new ArrayList<>();
		
		todoItemsWebElement = driver.findElements(listItems);
		for (WebElement webElement : todoItemsWebElement) {			
			if(webElement.getText().equals(oldItem)) {
				WebElement oldElement = driver.findElement(By.xpath ("//*[contains(text(),'" + oldItem + "')]"));
				
				action.doubleClick(oldElement).perform();			
				
				driver.findElement(editItem).sendKeys(Keys.CONTROL + "a", Keys.DELETE);
				driver.findElement(editItem).sendKeys(newItem);
				driver.findElement(editItem).sendKeys(Keys.ENTER);
								
			}

		}
	}
	
	public void completeToDoItem(String toDoItem) {
		driver.findElement(By.xpath ("//*[contains(text(),'" + toDoItem + "')]/preceding-sibling::input")).click();
		driver.findElement(clearComplete).click();
	}
	
	public boolean checkClearCompletedLabel(String label) {
		try {
		driver.findElement(By.xpath ("//*[contains(text(),'" + label + "')]"));
		}
		catch(Exception e) {
			return true;
		}
		return false;
		
	}
}
