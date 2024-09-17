import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LogInSuccess extends JPanel
{
    private UserManager userManager;
    private MainFrame mainFrame;
    private LoginPage loginPage;
    
    public LogInSuccess(MainFrame mainFrame, UserManager userManager, LoginPage loginPage) throws IOException
    {
        this.userManager = userManager;
        this.mainFrame = mainFrame;
        this.loginPage = loginPage;
        
        setBackground(new Color(110, 20, 90));
        setLayout(null);

        Rectangle();
        RememberMeBox();
    }

    private void Rectangle()
    {
        JTextArea logInSuccess = new JTextArea("Log In Successful");
        logInSuccess.setBounds(50, 300, 300, 160);
        logInSuccess.setEditable(false);
        logInSuccess.setFont(new Font("Arial", Font.CENTER_BASELINE, 55));
        logInSuccess.setBackground(Color.white);
        logInSuccess.setForeground(Color.black);
        logInSuccess.setLineWrap(true);
        logInSuccess.setWrapStyleWord(true);
        
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
        logInSuccess.setBorder(lineBorder);

        add(logInSuccess);
    }

    public boolean rememberUser = false;
    // decides if user's credentails will be remembered or not

    private void RememberMeBox()
    {
        JTextArea question = new JTextArea();
        question.setText("Would you like us to remember you?");
        question.setBounds(50, 480, 300, 50);
        question.setEditable(false);
        question.setFont(new Font("Arial", Font.BOLD, 18));
        question.setForeground(Color.WHITE);
        question.setBackground(new Color(0,0,0,0));
        question.setLineWrap(true);
        question.setWrapStyleWord(true);

        JCheckBox rememberMe = new JCheckBox("Yes, remember me");
        rememberMe.setBounds(50, 520, 300, 50);
        rememberMe.setFont(new Font("Arial", Font.BOLD, 18));
        rememberMe.setForeground(Color.white);
        rememberMe.setBackground(new Color(0,0,0,0));

        rememberMe.addActionListener(new ActionListener() // checks if user checked the checkbox :)
        {
            @Override
            public void actionPerformed(ActionEvent checked)
            {
                if(rememberMe.isSelected())
                {
                    System.out.println("checked!");
                    rememberUser = true;

                    //userManager.setLogInSuccess(successfulLogIn);
                    userManager.checkRememberStatus();
                    System.out.println("mail: " + loginPage.input_mail);
                    userManager.RememberUser(loginPage.input_mail, loginPage.user_password);
                }
                else
                {
                    System.out.println("no no!");
                }
            }
        });

        add(question);
        add(rememberMe);
    }
}