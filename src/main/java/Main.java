import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    private final JTextField plaintextField = new JTextField(20);
    private final JTextField keyField = new JTextField(20);
    private final JTextField ciphertextField = new JTextField(20);
    private final JTextField decryptedtextField = new JTextField(20);
    private final JComboBox<String> modeComboBox = new JComboBox<>(new String[]{"ECB", "CBC", "CFB", "OFB"});

    public Main() {
        super("DES Encryption and Decryption");

        JLabel plaintextLabel = new JLabel("Plaintext:");
        JLabel keyLabel = new JLabel("Key:");
        JLabel ciphertextLabel = new JLabel("Ciphertext:");
        JLabel decryptedLabel = new JLabel("Decrypted Text:");
        JLabel modeLabel = new JLabel("Encryption Mode:");

        JButton encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(this);

        JButton decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(this);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(plaintextLabel);
        inputPanel.add(plaintextField);
        inputPanel.add(keyLabel);
        inputPanel.add(keyField);
        inputPanel.add(modeLabel);
        inputPanel.add(modeComboBox);

        JPanel outputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        outputPanel.add(ciphertextLabel);
        outputPanel.add(ciphertextField);
        outputPanel.add(decryptedLabel);
        outputPanel.add(decryptedtextField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);

        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(outputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }


    public void actionPerformed(ActionEvent e) {
        try {
            String key = keyField.getText();
            String iv = "00000000";  //visada esantis
            String plaintext = plaintextField.getText();
            String mode = (String) modeComboBox.getSelectedItem();

            if (e.getActionCommand().equals("Encrypt")) {

                String ciphertext = "";


                switch (mode) {
                    case "ECB":
                        ciphertext = ECBMode.encrypt(plaintext, key);
                        break;
                    case "CBC":
                        ciphertext = CBCMode.encrypt(plaintext, key, iv);
                        break;
                    case "CFB":
                        // call CFB encryption method here
                        break;
                    case "OFB":
                        // call OFB encryption method here
                        break;
                }
                ciphertextField.setText(ciphertext);
            } else if (e.getActionCommand().equals("Decrypt")) {

                String ciphertext = ciphertextField.getText();
                String decrypted = "";

                switch (mode) {
                    case "ECB":
                        decrypted = ECBMode.decrypt(ciphertext, key);
                        break;
                    case "CBC":
                        decrypted = CBCMode.decrypt(ciphertext, key, iv);
                        break;
                    case "CFB":
                        // call CFB decryption method here
                        break;
                    case "OFB":
                        // call OFB decryption method here
                        break;
                }

                decryptedtextField.setText(decrypted);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error during encryption: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);



        }
    }
    public static void main (String[]args){
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}


