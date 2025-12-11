//Project Name: The Coding Library
//Author: Ryan Roberts
//Date: 12/12/2025
//This class provides a graphical user interface using Java Swing for the library management system.

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LibraryGUI extends JFrame {
    private LibraryCatalog catalog;
    private DefaultTableModel tableModel;
    private JTable bookTable;
    private JTextField isbnField, titleField, authorField;
    private JTextArea outputArea;
    
    public LibraryGUI() {
        // Initialize the catalog
        catalog = new LibraryCatalog();
        
        // Initialize sample books
        initializeSampleBooks();
        
        // Set up the JFrame
        setTitle("The Coding Library - Management System");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create the main layout
        setLayout(new BorderLayout(10, 10));
        
        // Create and add components
        add(createTitlePanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
        
        // Force an initial table update after all components are created
        SwingUtilities.invokeLater(() -> {
            updateBookTable();
            revalidate();
            repaint();
        });
        
        // Display the window
        setVisible(true);
    }
    
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(51, 102, 153));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        
        JLabel titleLabel = new JLabel("📚 The Coding Library");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        
        titlePanel.add(titleLabel);
        return titlePanel;
    }
    
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create the book table
        String[] columnNames = {"Title", "Author", "ISBN", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        bookTable = new JTable(tableModel);
        bookTable.setFont(new Font("Arial", Font.PLAIN, 12));
        bookTable.setRowHeight(25);
        bookTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        bookTable.getTableHeader().setBackground(new Color(51, 102, 153));
        bookTable.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane tableScrollPane = new JScrollPane(bookTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Book Collection"));
        
        // Create input form panel (without buttons)
        JPanel inputFormPanel = createInputFormPanel();
        
        // Create button panel separately
        JPanel buttonPanel = createButtonPanel();
        
        // Create a container for form and buttons
        JPanel inputContainer = new JPanel(new BorderLayout(0, 10));
        inputContainer.add(inputFormPanel, BorderLayout.CENTER);
        inputContainer.add(buttonPanel, BorderLayout.SOUTH);
        
        // Split the center into table and input
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tableScrollPane, inputContainer);
        splitPane.setDividerLocation(300);
        splitPane.setResizeWeight(0.6);
        
        centerPanel.add(splitPane, BorderLayout.CENTER);
        
        // Update table with initial data
        updateBookTable();
        
        return centerPanel;
    }
    
    private JPanel createInputFormPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Book Management"),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        
        // Create form panel with GridBagLayout for better control
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 5, 8, 5);
        
        // ISBN Row
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        JLabel isbnLabel = new JLabel("ISBN:");
        isbnLabel.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(isbnLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        isbnField = new JTextField(20);
        isbnField.setFont(new Font("Arial", Font.PLAIN, 13));
        isbnField.setPreferredSize(new Dimension(250, 28));
        formPanel.add(isbnField, gbc);
        
        // Title Row
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.2;
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(titleLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        titleField = new JTextField(20);
        titleField.setFont(new Font("Arial", Font.PLAIN, 13));
        titleField.setPreferredSize(new Dimension(250, 28));
        formPanel.add(titleField, gbc);
        
        // Author Row
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.2;
        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(authorLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        authorField = new JTextField(20);
        authorField.setFont(new Font("Arial", Font.PLAIN, 13));
        authorField.setPreferredSize(new Dimension(250, 28));
        formPanel.add(authorField, gbc);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.setPreferredSize(new Dimension(600, 140));
        mainPanel.setMinimumSize(new Dimension(400, 140));
        
        return mainPanel;
    }
    
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        
        JButton addButton = createStyledButton("Add Book", new Color(46, 125, 50));
        JButton checkOutButton = createStyledButton("Check Out", new Color(25, 118, 210));
        JButton checkInButton = createStyledButton("Check In", new Color(251, 140, 0));
        JButton refreshButton = createStyledButton("Refresh", new Color(117, 117, 117));
        
        // Add action listeners
        addButton.addActionListener(e -> addBook());
        checkOutButton.addActionListener(e -> checkOutBook());
        checkInButton.addActionListener(e -> checkInBook());
        refreshButton.addActionListener(e -> updateBookTable());
        
        buttonPanel.add(addButton);
        buttonPanel.add(checkOutButton);
        buttonPanel.add(checkInButton);
        buttonPanel.add(refreshButton);
        
        return buttonPanel;
    }
    
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        
        outputArea = new JTextArea(5, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setBackground(new Color(240, 240, 240));
        outputArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("System Messages"));
        
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        
        return bottomPanel;
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(120, 35));
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    private void initializeSampleBooks() {
        catalog.addBook(new Book("Java Programming", "John Smith", "ISBN001"));
        catalog.addBook(new Book("Python Basics", "Jane Doe", "ISBN002"));
        catalog.addBook(new Book("JavaScript Essentials", "Bob Johnson", "ISBN003"));
        catalog.addBook(new Book("C++ Fundamentals", "Alice Williams", "ISBN004"));
        catalog.addBook(new Book("Data Structures & Algorithms", "Michael Brown", "ISBN005"));
        catalog.addBook(new Book("Web Development with React", "Sarah Davis", "ISBN006"));
    }
    
    private void updateBookTable() {
        // Clear the table
        tableModel.setRowCount(0);
        
        // Get all books from the catalog
        ArrayList<Book> books = catalog.getAllBooks();
        
        // Add each book to the table
        for (Book book : books) {
            String status = book.isCheckedOut() ? "Checked Out" : "Available";
            Object[] rowData = {book.getTitle(), book.getAuthor(), book.getIsbn(), status};
            tableModel.addRow(rowData);
        }
        
        logMessage("Book table refreshed. Total books: " + books.size());
    }
    
    private void addBook() {
        String isbn = isbnField.getText().trim();
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        
        if (isbn.isEmpty() || title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please fill in all fields (ISBN, Title, and Author).", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Book newBook = new Book(title, author, isbn);
        catalog.addBook(newBook);
        
        // Refresh the entire table
        updateBookTable();
        
        // Clear fields
        isbnField.setText("");
        titleField.setText("");
        authorField.setText("");
        
        logMessage("✓ Book added: " + title + " by " + author + " (ISBN: " + isbn + ")");
    }
    
    private void checkOutBook() {
        int selectedRow = bookTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a book from the table to check out.", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String title = (String) tableModel.getValueAt(selectedRow, 0);
        String status = (String) tableModel.getValueAt(selectedRow, 3);
        
        if (status.equals("Checked Out")) {
            JOptionPane.showMessageDialog(this, 
                "This book is already checked out.", 
                "Already Checked Out", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        catalog.checkOutBook(title);
        updateBookTable();
        
        logMessage("✓ Checked out: " + title);
    }
    
    private void checkInBook() {
        int selectedRow = bookTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a book from the table to check in.", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String title = (String) tableModel.getValueAt(selectedRow, 0);
        String status = (String) tableModel.getValueAt(selectedRow, 3);
        
        if (status.equals("Available")) {
            JOptionPane.showMessageDialog(this, 
                "This book is not checked out.", 
                "Not Checked Out", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        catalog.returnBook(title);
        updateBookTable();
        
        logMessage("✓ Checked in: " + title);
    }
    
    private void logMessage(String message) {
        if (outputArea != null) {
            outputArea.append(message + "\n");
            outputArea.setCaretPosition(outputArea.getDocument().getLength());
        }
    }
    
    public static void main(String[] args) {
        // macOS-specific system properties for proper GUI display
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("apple.awt.application.name", "The Coding Library");
        System.setProperty("apple.awt.application.appearance", "system");
        
        // Use SwingUtilities to ensure GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Set the system look and feel for better appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            new LibraryGUI();
        });
    }
}
