// Import necessary 
import java.sql.*;
import java.util.*;

// Define a User class to store user information
class User {
    int id;
    String username;
    String password;

    // Getters and setters
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

// Define a StackOperation class to store stack operations
class StackOperation {
    int id;
    int userId;
    String operation;
    String value;
    Timestamp timestamp;

    // Getters and setters for stack operation attributes
    public int getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }
    public String getOperation() {
        return operation;
    }
    public String getValue() {
        return value;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}

// Define a UserStack class to perform stack operations
class UserStack{
    Stack<String> stack;
    int userId;
    
    // Constructor to initialize the stack and user ID
    public UserStack(int userId){
        this.stack = new Stack<>();
        this.userId = userId;
    }

    // Method to push an element onto the stack
    public void push(String value) throws Exception{
        try{
            stack.push(value);
            ApplicationOfStack.recordOperation(userId, "push", value);
            ApplicationOfStack.getOperations(userId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
    }

    // Method to pop an element from the stack
    public String pop() throws Exception{
        if(!stack.isEmpty()){
            String value = stack.pop();
            ApplicationOfStack.recordOperation(userId, "pop", value);
            ApplicationOfStack.getOperations(userId);
            return value;
        }
        else{
            return null;
        }
        
    }

    // Method to peek at the top element of the stack
    public String peek() throws Exception{
        if(!stack.isEmpty()){
            String value = stack.peek();
            ApplicationOfStack.recordOperation(userId, "peek", value);
            ApplicationOfStack.getOperations(userId);
            return value;
        }
        else{
            return null;
        }

    }

    // Method to check if the stack is empty
    public boolean isEmpty() throws Exception{
        boolean empty = stack.isEmpty();
        ApplicationOfStack.recordOperation(userId, "isEmpty", String.valueOf(empty));
        ApplicationOfStack.getOperations(userId);
        return empty;
    }

    // Method to get the size of the stack
    public int size() throws Exception{
        int size = stack.size();
        ApplicationOfStack.recordOperation(userId, "size", String.valueOf(size));
        ApplicationOfStack.getOperations(userId);
        return size;
    }

    // Method to clear the stack
    public void clear() throws Exception{
        stack.clear();
        ApplicationOfStack.recordOperation(userId, "clear", "Stack cleared");
        ApplicationOfStack.getOperations(userId);
    }

    // Methods to perform expression conversions
    public void infixPostfix(String expression) throws Exception{
        ApplicationOfStack.recordOperation(userId, "Infix to Postfix",expression);
        ApplicationOfStack.getOperations(userId);
    }
    public void infixPrefix(String expression) throws Exception{
        ApplicationOfStack.recordOperation(userId, "Infix to Prefix",expression);
        ApplicationOfStack.getOperations(userId);
    }
    public void postfixInfix(String expression) throws Exception{
        ApplicationOfStack.recordOperation(userId, "Postfix to Infix",expression);
        ApplicationOfStack.getOperations(userId);
    }
    public void pretfixInfix(String expression) throws Exception{
        ApplicationOfStack.recordOperation(userId, "Prefix to Infix",expression);
        ApplicationOfStack.getOperations(userId);
    }
    public void postfixPrefix(String expression) throws Exception{
        ApplicationOfStack.recordOperation(userId, "Postfix to Prefix",expression);
        ApplicationOfStack.getOperations(userId);
    }
    public void prefixPostfix(String expression) throws Exception{
        ApplicationOfStack.recordOperation(userId, "Prefix to Postfix",expression);
        ApplicationOfStack.getOperations(userId);
    }

}

// Define an ExpressionConverter class to perform expression conversions
class ExpressionConverter {

    // Method to determine operator precedence
    public static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Method to reverse a string
    static String reverse(String exp) {
        String result = "";
        for (int i = exp.length() - 1; i >= 0; i--) {
            result += exp.charAt(i);
        }
        return result;
    }

    // Method to check if an expression has a valid operator-operand ratio
    static boolean rankChecker(String expression){
        int operator = 0;
        int operand = 0;
        for(int i=0;i<expression.length();i++){
            if(isOperator(expression.charAt(i))){
                operator++;
            }
            else{
                operand++;
            }
        }
        if((operand-operator)==1){
            return true;
        }
        return false;
    }

    // Method to convert infix to postfix expression
    static String infixToPostfix(String expression){
        try {
            if(rankChecker(expression)){
                String result = "";
                Stack<Character> stack = new Stack<>();
                for(int i=0;i<expression.length();i++){
                    char c = expression.charAt(i);
                    if(Character.isLetterOrDigit(c)){
                        result += c;
                    }
                    else if(c=='('){
                        stack.push(c);
                    }
                    else if(c==')'){
                        while (!stack.isEmpty() && stack.peek()!='(') {
                            result += stack.pop();
                            
                        }
                        stack.pop();
                    }
                    else{
                        while (!stack.isEmpty() && precedence(c)<=precedence(stack.peek())) {
                            result += stack.pop();  
                        }
                        stack.push(c);
                    }
                }
                while (!stack.isEmpty()) {
                    result += stack.pop();       
                            
                }
                return result.toString();
            }
            return null;
        } catch (Exception e) {
            
            return null;
        }
        
          
    }

    // Method to convert infix to prefix expression
    static String infixToPrefix(String expression) {
        try {
            if(rankChecker(expression)){
                expression = reverse(expression);
                String modifiedExpression = "";
                for(int i=0;i<expression.length();i++){
                    char c = expression.charAt(i);
                    if(c=='('){
                        modifiedExpression += ')';

                    }
                    else if(c==')'){
                        modifiedExpression += '(';
                    }
                    else{
                        modifiedExpression += c;
                    }
                }
                String postfix = infixToPostfix(modifiedExpression);

                String prefix = reverse(postfix);
                return prefix;
            }
            return null;
        }catch (Exception e) {
            
            return null;
        }
        
    }

    // Method to check if a character is an operator
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    // Method to convert postfix to infix expression
    static String postfixToInfix(String expression) {
        try {
            if(rankChecker(expression)){
                Stack<String> stack = new Stack<>();
                for(int i =0;i<expression.length();i++){
                    char c = expression.charAt(i);
                    if(isOperator(c)){
                        String operand2 = stack.pop();
                        String operand1 = stack.pop();
                        String subExpression = "(" + operand1 + c + operand2 +")";
                        //System.out.println(subExpression);
                        stack.push(subExpression);
                    }
                    else{
                        stack.push(c + "");
                    }
                }
                return stack.pop();
            }
            return null;
        } catch (Exception e) {
            
            return null;
        }
    }

    // Method to convert prefix to infix expression
    static String prefixToInfix(String expression) {
        try {
            if(rankChecker(expression)){
                Stack<String> stack = new Stack<>();
                for(int i=expression.length()-1;i>=0;i--){
                    char c = expression.charAt(i);
                    if(isOperator(c)){
                        String operand1 = stack.pop();
                        String operand2 = stack.pop();
                        String subExpression = "(" + operand1 + c + operand2 + ")";
                        stack.push(subExpression);
                    }
                    else{
                        stack.push(c +"");
                    }
                }
                return stack.pop();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    // Method to convert postfix to prefix 
    static String postfixToPrefix(String expression) {
        try {
            if(rankChecker(expression)){
                String infix = postfixToInfix(expression);
                return infixToPrefix(infix);
            }
            return null;
        } catch (Exception e) {
            return null;
        }    
    }

    // Method to convert prefix to postfix expression
    static String prefixToPostfix(String expression) {

        try {
            // Check if the expression has a valid operator-operand ratio
            if(rankChecker(expression)){

                // Convert prefix to infix expression
                String infix = prefixToInfix(expression);

                // Convert infix to postfix expression
                return infixToPostfix(infix);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
        
        
    }
}

// Class for the stack application
class ApplicationOfStack {

    // Database connection and statement
    static Connection con;
    static Statement st;

    // Scanner for user input
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception{

        // Database URL, username, and password
        String dburl = "jdbc:mysql://localhost:3306/StackApp";
        String dbuser = "root";
        String dbpass = "";

        // Establish database connection
        con = DriverManager.getConnection(dburl, dbuser, dbpass);
        System.out.println((con!=null)?"Connection done":"Connection failed");

        // Create statement
        st = con.createStatement();

        String q = "Create table if not exists Users(id int auto_increment primary key,username varchar(50) not null unique,password varchar(20) not null)";
        st.execute(q);
        String q1 = "Create table if not exists stack_operations(id int auto_increment primary key,user_id int,operation varchar(50),value varchar(50),time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,foreign key(user_id) references Users(id))";
        st.execute(q1);

        // Main loop
        boolean b = true;
        while (b) {

            // Display menu
            System.out.println("=========== WELCOME TO STACK APPLICATION ===========");
            System.out.println("Enter 1 to Register User.");
            System.out.println("Enter 2 to Login User if Registered.");
            System.out.println("Enter 3 to Exit.");
            System.out.println("Please enter your choice:");
            int choice = sc.nextInt();
            try{

                // Handle user choice
                switch (choice) {
                    case 1:
                    register();
                    break;
                    case 2:
                    login();
                    break;
                    case 3:
                    System.out.println("Thank You For Using Our Application :)");
                    b = false;
                    break;
                    default:
                    System.out.println("Please enter valid choice.");
                    break;
                   
                }
            }catch(Exception e){
                e.printStackTrace();
            }            
        }
    }

    // Method to register a user
    static void register() throws Exception{
        System.out.println("Enter username:");
        String username = sc.next();
        System.out.println("Enter password:");
        String passWord = sc.next();

        User user = new User();
        user.setUsername(username);
        user.setPassword(passWord);
        registerUser(user);
        System.out.println("User registered successfully!");

    }

    // Method to register a user in the database
    static public void registerUser(User user) throws Exception{
        String q = "Insert into Users(username,password) values(?,?)";
        try(PreparedStatement pst = con.prepareStatement(q)){
            pst.setString(1,user.getUsername());
            pst.setString(2, user.getPassword());
            pst.executeUpdate();
 
        }
    }

    // Method to login a user
    static void login() throws Exception{
        System.out.println("Enter username:");
        String username = sc.next();
        System.out.println("Enter password:");
        String passWord = sc.next();

        User user = loginUser(username, passWord);
        if(user!=null){
            System.out.println("Login Successfull!");
            userStackOperation(user);
        }
        else{
            System.out.println("Invalid username or password!");
        }
    }

    // Method to login a user from the database
    static public User loginUser(String username,String password) throws Exception{
        String q = "Select * from Users where username = ? and password = ?";
        try( PreparedStatement pst = con.prepareStatement(q)){
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                return user;
            }
        }
        return null;
    }

    // Method to perform stack operations for a user
    static void userStackOperation(User user) throws Exception{
        UserStack uStack = new UserStack(user.getId());
        boolean b = true;
        while (b) {

            // Display stack operations menu
            System.out.println("======== Basic Functions of Stack=======");
            System.out.println("Enter 1 to push the element.");
            System.out.println("Enter 2 to pop the element.");
            System.out.println("Enter 3 to peek the element.");
            System.out.println("Enter 4 to check whether stack is empty or not.");
            System.out.println("Enter 5 to get the size of the stack.");
            System.out.println("Enter 6 to clear the stack.");
            System.out.println("============= Conversions ==============");
            System.out.println("Enter 7 to convert Infix to Postfix Expression.");
            System.out.println("Enter 8 to convert Infix to Prefix Expression.");
            System.out.println("Enter 9 to convert Postfix to Infix Expression.");
            System.out.println("Enter 10 to convert Prefix to Infix Expression.");
            System.out.println("Enter 11 to convert Postfix to Prefix Expression.");
            System.out.println("Enter 12 to convert Prefix to Postfix Expression.");
            System.out.println("Enter 13 to Logout.");
            
            System.out.println("Enter your choice:");
            int choice = sc.nextInt();
            
            // Method to handle user choice for stack operations
            switch (choice) {

                // Push element onto the stack
                case 1:
                System.out.println("Enter value to push:");
                sc.nextLine();
                String value = sc.nextLine();
                uStack.push(value);
                System.out.println("Value "+value+" pushed!");
                break;

                // Pop element from the stack
                case 2:
                String value1 = uStack.pop();
                System.out.println("Popped value: "+value1);
                break;

                // Peek at the top element of the stack
                case 3:
                String value2 = uStack.peek();
                System.out.println("Peeked value: "+value2);
                break;

                // Check if the stack is empty
                case 4:
                boolean isEmpty = uStack.isEmpty();
                System.out.println("Is stack Empty: "+isEmpty);
                break;

                // Get the size of the stack
                case 5:
                int size = uStack.size();
                System.out.println("Size of Stack: "+size);
                break;

                // Clear the stack
                case 6:
                uStack.clear();
                System.out.println("Stack cleared!");
                break;

                // Convert infix to postfix expression
                case 7:
                System.out.println("Enter Infix Expression:");
                String infix = sc.next();
                String postfix = ExpressionConverter.infixToPostfix(infix);
                if(postfix!=null){
                    System.out.println("Postfix: "+ postfix);
                    uStack.infixPostfix(postfix);
                }
                else{
                    System.out.println("Invalid expression.");
                }
                break;

                // Convert infix to prefix expression
                case 8:
                System.out.println("Enter Infix Expression:");
                String infix1 = sc.next();
                String prefix = ExpressionConverter.infixToPrefix(infix1);
                if(prefix!=null){
                    System.out.println("Prefix: "+ prefix);
                    uStack.infixPrefix(prefix);
                }
                else{
                    System.out.println("Invalid expression.");
                }
                break;

                // Convert postfix to infix expression
                case 9:
                System.out.println("Enter Postfix Expression:");
                String postfix1 = sc.next();
                String infixString = ExpressionConverter.postfixToInfix(postfix1);
                if(infixString!=null){
                    System.out.println("Infix: "+infixString);
                    uStack.postfixInfix(infixString);
                }
                else{
                    System.out.println("Invalid expression.");
                }
                break;

                // Convert prefix to infix expression
                case 10:
                System.out.println("Enter Prefix Expression:");
                String prefix1 = sc.next();
                String infixString1 = ExpressionConverter.prefixToInfix(prefix1);
                if(infixString1!=null){
                    System.out.println("Infix: "+infixString1);
                    uStack.pretfixInfix(infixString1);
                }
                else{
                    System.out.println("Invalid expression.");
                }
                break;

                // Convert postfix to prefix expression
                case 11:
                System.out.println("Enter Postfix Expression:");
                String postfix2 = sc.next();
                String prefixString = ExpressionConverter.postfixToPrefix(postfix2);
                if(prefixString!=null){
                    System.out.println("Prefix: "+prefixString);
                    uStack.postfixPrefix(prefixString);
                }
                else{
                    System.out.println("Invalid expression.");
                }
                break;

                // Convert prefix to postfix expression
                case 12:
                System.out.println("Enter Prefix Expression:");
                String prefix2 = sc.next();
                String postfixString = ExpressionConverter.prefixToPostfix(prefix2);
                if(postfixString!=null){
                    System.out.println("Postfix: "+postfixString);
                    uStack.prefixPostfix(postfixString);
                }
                else{
                    System.out.println("Invalid expression.");
                }
                
                break;

                // Logout
                case 13:
                System.out.println("Thank you :) ");
                b = false;
                break;

                // Invalid choice
                default:
                System.out.println("Enter valid choice.");
                break;
            }
        }
    }

    // Method to record a stack operation in the database
    static public void recordOperation(int userId,String operation,String value) throws Exception{

        // SQL query to insert a new operation into the stack_operations table
        String q = "Insert into stack_operations (user_id,operation,value) values (?,?,?)";
        try(PreparedStatement pst = con.prepareStatement(q)){

            // Set the user ID, operation, and value parameters
            pst.setInt(1, userId);
            pst.setString(2, operation);
            pst.setString(3, value);

            // Execute the query
            pst.executeUpdate();

            // Retrieve and display the recorded operation
            q = "select * from stack_operations";
            PreparedStatement pst1 = con.prepareStatement(q);
            
            ResultSet rs = pst1.executeQuery();
            if(rs.next()){

                // Create a new StackOperation object to store the retrieved data
                StackOperation sp = new StackOperation();

                sp.setId(rs.getInt(1)); // Set the operation ID
                sp.setUserId(rs.getInt(2)); // Set the user ID
                sp.setOperation(rs.getString(3)); // Set the operation
                sp.setValue(rs.getString(4)); // Set the value
                sp.setTimestamp(rs.getTimestamp(5)); // Set the timestamp
            }
                
        }
    }

    // Method to retrieve a list of stack operations for a given user
    static public List<StackOperation> getOperations(int userId) throws Exception{

        // SQL query to select all operations for the given user
        String q = "Select * from stack_operations where user_id = ?";

        List<StackOperation> operations = new ArrayList<>();    // List to store the operations

        try(PreparedStatement pst = con.prepareStatement(q)){

            // Set the user ID parameter
            pst.setInt(1, userId);

            // Execute the query
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                // Create a new StackOperation object to store the retrieved data
                StackOperation operation = new StackOperation();

                operation.setId(rs.getInt(1)); // Set the operation ID
                operation.setUserId(rs.getInt(2)); // Set the user ID
                operation.setOperation(rs.getString(3)); // Set the operation
                operation.setValue(rs.getString(4)); // Set the value
                operation.setTimestamp(rs.getTimestamp(5)); // Set the timestamp

                // Add the operation to the list
                operations.add(operation);

            }
        }

        // Return the list of operations
        return operations;
    }
}