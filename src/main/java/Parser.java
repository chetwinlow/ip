class Parser {

    public static Command parse(String input) throws IllegalCommandException, EmptyDescriptionException {
        if (input.equals("bye")) {
            return new Exit();
        } else if (input.equals("list")) {
            return new List();
        } else if (input.startsWith("done")) {
            int value = Character.getNumericValue(input.charAt(5));
            return new Done(value);
        }  else if (input.startsWith("delete")) {
            int value = Character.getNumericValue(input.charAt(7));
            return new Delete(value);
        } else {
            try {
                return new Add(input);
            } catch (IllegalCommandException | EmptyDescriptionException e) {
                throw e;
            }
        }
    }
}
