package duke.task;

import duke.task.Task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    public String fileFormat() {
        return String.format("E%s | %s", super.fileFormat(), this.at);
    }
}