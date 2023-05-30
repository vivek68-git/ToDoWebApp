package stepdefinitions;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.TodoMvcPage;

public class ToDoItemsSteps {

	private TodoMvcPage todoMvcPage = new TodoMvcPage(DriverFactory.getDriver());

	@Given("user launches the ToDo MVC application")
	public void user_launches_the_to_do_mvc_application() throws InterruptedException {

		DriverFactory.getDriver()
		.get("https://todomvc.com/examples/angular2/");

	}

	@Given("User has not entered any todo items")
	public void user_has_not_entered_any_todo_items() throws InterruptedException {

		List<String> todoItems = todoMvcPage.getTodoItems();
		Assert.assertTrue(todoItems.isEmpty(), " ");

	}

	@When("User adds {string}")
	public void user_adds(String todoItem) throws InterruptedException {
		todoMvcPage.addTodoItem(todoItem);
	}

	@Then("todo list should contain:")
	public void todo_list_should_contain(io.cucumber.datatable.DataTable dataTable) {

		List<String> expectedToDoItems = new ArrayList<>();
		expectedToDoItems = dataTable.asList();

		List<String> actualToDoItems = todoMvcPage.getTodoItems();

		Assert.assertEquals(actualToDoItems, expectedToDoItems, "Added ToDo Items not matching");

	}

	@When("User adds multiple todo items:")
	public void user_adds_multiple_todo_items(io.cucumber.datatable.DataTable dataTable) {
		List<String> addMultipleToDoItems = dataTable.asList();
		for (String todoItem : addMultipleToDoItems) {
			todoMvcPage.addTodoItem(todoItem);
		}
	}

	@Then("User updates {string} to {string}")
	public void user_updates_to(String oldItem, String updatedItem) {
		todoMvcPage.updateToDoItem(oldItem, updatedItem);
	}
	
	@When("User completes and clears some todo Items:")
	public void user_completes_and_clears_some_todo_items(io.cucumber.datatable.DataTable dataTable) {
		List<String> completeToDoItems = dataTable.asList();
		for (String completeToDoItem : completeToDoItems) {
			todoMvcPage.completeToDoItem(completeToDoItem);
		}
	}
	
	@Then("{string} should not be visible")
	public void should_not_be_visible(String label) {
	   Assert.assertTrue(todoMvcPage.checkClearCompletedLabel(label), "Clear Completed Label still present");
	}


}
