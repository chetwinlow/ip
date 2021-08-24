class Add extends Command {

    private Task task;

    public Add(String input) throws IllegalCommandException, EmptyDescriptionException {
        if (input.startsWith("todo")) {
            String temp = input.substring(4);
            if (temp.length() == 0) {
                String message = "OOPS!!! The description of a todo cannot be empty.";
                throw new EmptyDescriptionException(message);
            }
            this.task = new Todo(temp.substring(1));
        } else if (input.startsWith("deadline")) {
            String[] arr = input.split(" /by ");
            this.task = new Deadline(arr[0].substring(9), arr[1]);
        } else if (input.startsWith("event")) {
            String[] arr = input.split("/at ");
            this.task = new Event(arr[0].substring(6), arr[1]);
        } else {
            throw new IllegalCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    public void exec(TaskList tasks, Ui ui, Storage storage) throws NoListException{
        tasks.add(this.task);
        try {
            storage.save(tasks);
            System.out.println("Got it. I've added this task:\n"
                    + this.task.toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.");
        } catch (NoListException e) {
            throw e;
        }
    }

}
