package todoly.controllers.actions;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import todoly.controllers.Controller;
import todoly.interfaces.TaskListInterface;
import todoly.model.Task;
import todoly.util.comparators.SortByDueDate;
import todoly.views.ActionView;

public class ListTasksByDueDateController extends Controller {

	public ListTasksByDueDateController(TaskListInterface taskList, Scanner scanner) {
		ActionView view = new ActionView();
		
		List<Task> tasks = taskList.getTasks();
		Collections.sort(tasks, new SortByDueDate());
		
		List<String> tasksParsed = tasksToStringList(taskList.getTasks());
		view.printList(errorMessage, tasksParsed);
		
		displayMenu(view, scanner);
	}
}
