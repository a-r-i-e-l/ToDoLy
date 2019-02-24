package todoly.enums;

public enum Context {
	MAIN_MENUE,
	
		LIST_TASKS,
		
		LIST_PROJECTS,
			FILTER_BY_PROJECT,
		
		ADD_TASK_TITLE,
			ADD_DUE_DATE,
				PIC_OR_ADD_PROJECT,
					SAVE_NEW_TASK,
		
		EDIT_TASK, //insert id
			MARK_TASK_AS_DONE,
			REMOVE_TASK,
			EDIT_TITLE,
				UPDATE_TITLE,
			EDIT_DUE_DATE,
				UPDATE_DUE_DATE,
			EDIT_PROJECT,
				UPDATE_PROJECT,
			
		SAVE_AND_QUIT
}
