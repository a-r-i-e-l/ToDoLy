package todoly.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import todoly.enums.Context;
import todoly.exceptions.InvalidUserInputException;
import todoly.interfaces.ServiceInterface;
import todoly.model.Project;
import todoly.model.Task;
import todoly.presenters.ListProjects;
import todoly.presenters.ListTasksMenue;
import todoly.presenters.MainMenue;
import todoly.presenters.Presenter;

public class TextBasedUserInterface extends ApplicationProgramInterface{
	
	private Map<Context,Presenter> presenters;
	private ServiceInterface taskService;
	private Context context;
	private Scanner scanner = new Scanner(System.in);
	private String input;
	
	
	public TextBasedUserInterface(ServiceInterface taskService) {
		this.taskService = taskService;
		context = Context.MAIN_MENUE;
		setViews();
	}
	
	public void run() {
		Presenter presenter = presenters.get(context);
		setPresenterProperties(presenter);
		presenter.displayView();
		input = scanner.nextLine();
		
		try {
			presenter.validateUserInput(input);
			context = presenter.getContext(input);
		} catch (InvalidUserInputException e) {
			presenter.setErrorMessage(e.getMessage());
		}
	}
	
	private void setPresenterProperties(Presenter presenter){
		switch (context) {
		case MAIN_MENUE:
			presenter.addPropertie(showTaskAmount());
			presenter.addPropertie(showTaskDoneAmount());
			break;
		case LIST_TASKS:
			presenter.setProperties(showTasksByDueDate());
			break;
		case LIST_PROJECTS:
			presenter.setProperties(showProjects());
			break;
		}
		
	}

	@Override
	protected String showTaskAmount() {
		return Integer.toString(taskService.getTaskAmount());
	}

	@Override
	protected String showTaskDoneAmount() {
		return Integer.toString(taskService.getTaskDoneAmount());
	}
	
	@Override
	protected List<String> showTasksByDueDate(){
		return taskService.listTasksByDueDate().stream()
											   .map(Task::toString)
											   .collect(Collectors.toList());
	}
	
	@Override
	protected List<String> showProjects(){
		return taskService.listProjects().stream()
									     .map(Project::toString)
									     .collect(Collectors.toList());
	}
	
	@Override
	public void save() {
		taskService.save();
		scanner.close();
	}

	public boolean isRunning() {
		return context != Context.SAVE_AND_QUIT;
	}
	
	@SuppressWarnings("serial")
	private void setViews() {
		presenters = new HashMap<Context, Presenter>(){{
						put(Context.MAIN_MENUE,new MainMenue());
						put(Context.LIST_TASKS,new ListTasksMenue());
						put(Context.LIST_PROJECTS,new ListProjects());
					}};
	}
}
