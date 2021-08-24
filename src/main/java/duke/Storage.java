package duke;

import duke.dukeexception.NoListException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a wrapper class that deals with loading and saving tasks in a file.
 */
public class Storage {
    private File file;

    /**
     * Constructs a Storage from a given filePath.
     *
     * @param filePath The given String filePath.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        if (!this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdirs();
        }
    }

    /**
     * Converts a string that contains info about a Task into a Task.
     *
     * @param fullString The given string
     * @return The Task with the appropriate information.
     */
    public static Task stringToTask(String fullString) {
        String[] arr = fullString.split("\\|");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }
        Task curr;
        if (arr[0].equals("T")) {
            curr = new Todo(arr[2]);
        } else if (arr[0].equals("E")) {
            curr = new Event(arr[2], arr[3]);
        } else {
            curr = new Deadline(arr[2], arr[3]);
        }
        if (arr[1].equals("1")) {
            curr.markAsDone();
        }
        return curr;
    }

    /**
     * Converts a Task into a string.
     *
     * @param task THe given Task.
     * @return The string to be stored into the file.
     */
    public static String taskToString(Task task) {
        return task.fileFormat();
    }

    /**
     * Returns an ArrayList<Task> that contains all the tasks in a give file.
     *
     * @return the arraylist of Task inside the fiven file.
     * @throws NoListException If there is no file to be loaded.
     */
    public ArrayList<Task> load() throws NoListException {
        try {
            Scanner sc = new Scanner(this.file);
            ArrayList<Task> temp = new ArrayList<>();
            while (sc.hasNextLine()) {
                String currTask = sc.nextLine();
                Task task = Storage.stringToTask(currTask);
                temp.add(task);
            }
            return temp;
        } catch (IOException e) {
            throw new NoListException(new IOException());
        }
    }

    /**
     * Saves the tasks in a tasklist into the file associated with this storage.
     *
     * @param tasks The given TaskList.
     * @throws NoListException If there is no file to be saved.
     */
    public void save(TaskList tasks) throws NoListException {
        try {
            FileWriter fw = new FileWriter(this.file);
            String curr= "";
            for (int i = 0; i < tasks.size(); i++) {
                curr += Storage.taskToString(tasks.get(i)) + "\n";
            }
            fw.write(curr);
            fw.close();
        } catch (IOException e) {
            throw new NoListException(new IOException());
        }
    }
}