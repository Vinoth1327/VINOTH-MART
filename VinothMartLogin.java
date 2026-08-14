import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

/**
 * VinothMartLogin.java
 * --------------------------------------------------------
 * A Java Swing login / register screen for "Vinoth Mart"
 * (an Instamart-style quick-commerce grocery app).
 *
 * Built for a capstone project first review.
 *
 * How to run:
 *   javac VinothMartLogin.java
 *   java VinothMartLogin
 * --------------------------------------------------------
 */
public class VinothMartLogin extends JFrame {

    // ---------- Theme colors (fresh grocery look, different from Ram Mart) ----------
    private static final Color LEAF_GREEN   = new Color(0x1E, 0x5F, 0x3A);
    private static final Color LEAF_DARK    = new Color(0x12, 0x3D, 0x24);
    private static final Color SUNSET       = new Color(0xFF, 0x7A, 0x3D);
    private static final Color CREAM        = new Color(0xFB, 0xF8, 0xF1);
    private static final Color INK          = new Color(0x1F, 0x24, 0x1E);
    private static final Color MUTED        = new Color(0x6E, 0x7A, 0x70);
    private static final Color FIELD_BORDER = new Color(0xD8, 0xE0, 0xD6);
    private static final Color CARD_WHITE   = Color.WHITE;

    // ---------- Fonts ----------
    private static final Font FONT_LOGO   = new Font("Georgia", Font.BOLD | Font.ITALIC, 30);
    private static final Font FONT_H1     = new Font("Georgia", Font.BOLD, 24);
    private static final Font FONT_SUB    = new Font("SansSerif", Font.PLAIN, 13);
    private static final Font FONT_LABEL  = new Font("SansSerif", Font.BOLD, 11);
    private static final Font FONT_INPUT  = new Font("SansSerif", Font.PLAIN, 14);
    private static final Font FONT_BTN    = new Font("SansSerif", Font.BOLD, 14);

    private CardLayout formCardLayout;
    private JPanel formCardPanel;
    private JPanel rootCardPanel;
    private CardLayout rootCardLayout;

    private JTextField loginIdField;
    private JPasswordField loginPwField;

    public VinothMartLogin() {
        setTitle("Vinoth Mart | Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 620);
        setMinimumSize(new Dimension(760, 560));
        setLocationRelativeTo(null);

        rootCardLayout = new CardLayout();
        rootCardPanel = new JPanel(rootCardLayout);

        rootCardPanel.add(buildAuthScreen(), "AUTH");
        rootCardPanel.add(buildWelcomeScreen(), "WELCOME");

        setContentPane(rootCardPanel);
        rootCardLayout.show(rootCardPanel, "AUTH");
    }

    // =========================================================
    //  AUTH SCREEN (split layout: brand panel + form panel)
    // =========================================================
    private JPanel buildAuthScreen() {
        JPanel auth = new JPanel(new GridBagLayout());
        auth.setBackground(CREAM);

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridy = 0;

        gc.gridx = 0;
        gc.weightx = 0.48;
        gc.weighty = 1;
        auth.add(buildBrandPanel(), gc);

        gc.gridx = 1;
        gc.weightx = 0.52;
        auth.add(buildFormPanel(), gc);

        return auth;
    }

    // ---------- Left brand / hero panel ----------
    private JPanel buildBrandPanel() {
        JPanel brand = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(
                        0, 0, LEAF_GREEN,
                        getWidth(), getHeight(), LEAF_DARK);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // decorative soft circles
                g2.setColor(new Color(255, 255, 255, 18));
                g2.fillOval(-60, -60, 220, 220);
                g2.setColor(new Color(255, 122, 61, 40));
                g2.fillOval(getWidth() - 180, getHeight() - 200, 260, 260);
                g2.dispose();
            }
        };
        brand.setBorder(new EmptyBorder(48, 44, 40, 44));

        // Top: logo
        JLabel logo = new JLabel("<html>Vinoth<span style='color:#FF7A3D;'>Mart</span></html>");
        logo.setFont(FONT_LOGO);
        logo.setForeground(CREAM);

        JLabel tag = new JLabel("INSTAMART · GROCERIES IN MINUTES");
        tag.setFont(new Font("SansSerif", Font.PLAIN, 11));
        tag.setForeground(new Color(255, 255, 255, 160));
        tag.setBorder(new EmptyBorder(6, 0, 0, 0));

        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);
        tag.setAlignmentX(Component.LEFT_ALIGNMENT);
        top.add(logo);
        top.add(tag);

        // Middle: headline
        JLabel headline = new JLabel("<html><div style='width:280px;'>Fresh groceries,<br>delivered to your<br>doorstep in 15 minutes.</div></html>");
        headline.setFont(new Font("Georgia", Font.PLAIN, 26));
        headline.setForeground(CREAM);

        JLabel sub = new JLabel("<html><div style='width:300px;color:rgba(255,255,255,0.75);'>Join thousands of happy customers shopping smarter with Vinoth Mart every single day.</div></html>");
        sub.setFont(FONT_SUB);
        sub.setForeground(new Color(255, 255, 255, 190));
        sub.setBorder(new EmptyBorder(14, 0, 0, 0));

        JPanel mid = new JPanel();
        mid.setOpaque(false);
        mid.setLayout(new BoxLayout(mid, BoxLayout.Y_AXIS));
        headline.setAlignmentX(Component.LEFT_ALIGNMENT);
        sub.setAlignmentX(Component.LEFT_ALIGNMENT);
        mid.add(headline);
        mid.add(sub);

        // Stats row
        JPanel stats = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 0));
        stats.setOpaque(false);
        stats.setBorder(new EmptyBorder(30, 0, 0, 0));
        stats.add(statBlock("15 min", "Avg. delivery"));
        stats.add(statBlock("2,000+", "Products"));
        stats.add(statBlock("50k+", "Happy users"));
        mid.add(Box.createVerticalStrut(4));
        mid.add(stats);

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        center.add(mid);

        // Bottom
        JLabel bottom = new JLabel("© 2026 Vinoth Mart  •  Privacy  •  Terms");
        bottom.setFont(new Font("SansSerif", Font.PLAIN, 11));
        bottom.setForeground(new Color(255, 255, 255, 130));

        brand.add(top, BorderLayout.NORTH);
        brand.add(center, BorderLayout.CENTER);
        brand.add(bottom, BorderLayout.SOUTH);

        return brand;
    }

    private JPanel statBlock(String num, String lbl) {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel n = new JLabel(num);
        n.setFont(new Font("Georgia", Font.BOLD, 20));
        n.setForeground(SUNSET);
        n.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel l = new JLabel(lbl);
        l.setFont(new Font("SansSerif", Font.PLAIN, 11));
        l.setForeground(new Color(255, 255, 255, 160));
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(n);
        p.add(l);
        return p;
    }

    // ---------- Right form panel ----------
    private JPanel buildFormPanel() {
        JPanel wrap = new JPanel(new GridBagLayout());
        wrap.setBackground(CREAM);

        JPanel card = new JPanel();
        card.setOpaque(false);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(360, 480));
        card.setMaximumSize(new Dimension(360, 600));

        // Tabs
        JPanel tabs = buildTabs();
        tabs.setAlignmentX(Component.LEFT_ALIGNMENT);

        formCardLayout = new CardLayout();
        formCardPanel = new JPanel(formCardLayout);
        formCardPanel.setOpaque(false);
        formCardPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formCardPanel.add(buildLoginPanel(), "LOGIN");
        formCardPanel.add(buildRegisterPanel(), "REGISTER");

        card.add(tabs);
        card.add(Box.createVerticalStrut(28));
        card.add(formCardPanel);

        wrap.add(card);
        return wrap;
    }

    private JButton tabLoginBtn, tabRegisterBtn;

    private JPanel buildTabs() {
        JPanel tabs = new JPanel(new GridLayout(1, 2, 0, 0));
        tabs.setBackground(new Color(0xEE, 0xF1, 0xEC));
        tabs.setBorder(new RoundedLineBorder(new Color(0xEE, 0xF1, 0xEC), 22, 4));
        tabs.setPreferredSize(new Dimension(360, 44));
        tabs.setMaximumSize(new Dimension(360, 44));

        tabLoginBtn = pillTabButton("Login", true);
        tabRegisterBtn = pillTabButton("Register", false);

        tabLoginBtn.addActionListener(e -> switchTab(true));
        tabRegisterBtn.addActionListener(e -> switchTab(false));

        tabs.add(tabLoginBtn);
        tabs.add(tabRegisterBtn);
        return tabs;
    }

    private JButton pillTabButton(String text, boolean active) {
        JButton b = new JButton(text);
        b.setFont(FONT_BTN);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setOpaque(true);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        styleTab(b, active);
        return b;
    }

    private void styleTab(JButton b, boolean active) {
        b.setBackground(active ? LEAF_GREEN : new Color(0, 0, 0, 0));
        b.setForeground(active ? CREAM : MUTED);
        b.setOpaque(active);
    }

    private void switchTab(boolean toLogin) {
        styleTab(tabLoginBtn, toLogin);
        styleTab(tabRegisterBtn, !toLogin);
        formCardLayout.show(formCardPanel, toLogin ? "LOGIN" : "REGISTER");
    }

    // ---------- Login panel ----------
    private JPanel buildLoginPanel() {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        JLabel h1 = leftLabel("Welcome back", FONT_H1, INK);
        JLabel sub = leftLabel("Login to continue shopping with Vinoth Mart", FONT_SUB, MUTED);
        sub.setBorder(new EmptyBorder(4, 0, 22, 0));

        loginIdField = new JTextField();
        JPanel idField = fieldBlock("Email or Mobile Number", loginIdField, null);

        loginPwField = new JPasswordField();
        JButton showPwBtn = smallLinkButton("Show");
        JPanel pwField = fieldBlock("Password", loginPwField, showPwBtn);
        showPwBtn.addActionListener(e -> togglePasswordVisibility(loginPwField, showPwBtn));

        JPanel forgotRow = new JPanel(new BorderLayout());
        forgotRow.setOpaque(false);
        forgotRow.setMaximumSize(new Dimension(360, 24));
        JButton forgot = smallLinkButton("Forgot password?");
        forgotRow.add(forgot, BorderLayout.EAST);
        forgotRow.setBorder(new EmptyBorder(0, 0, 18, 0));

        JButton loginBtn = primaryButton("Login");
        loginBtn.addActionListener(e -> doLogin());

        JLabel orLbl = leftLabel("or continue with", new Font("SansSerif", Font.PLAIN, 12), MUTED);
        orLbl.setHorizontalAlignment(SwingConstants.CENTER);
        orLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        orLbl.setBorder(new EmptyBorder(18, 0, 14, 0));

        JPanel socialRow = new JPanel(new GridLayout(1, 2, 12, 0));
        socialRow.setOpaque(false);
        socialRow.setMaximumSize(new Dimension(360, 42));
        socialRow.add(outlineButton("Google"));
        socialRow.add(outlineButton("OTP Login"));

        p.add(h1);
        p.add(sub);
        p.add(idField);
        p.add(Box.createVerticalStrut(14));
        p.add(pwField);
        p.add(forgotRow);
        p.add(loginBtn);
        p.add(orLbl);
        p.add(socialRow);

        return p;
    }

    // ---------- Register panel ----------
    private JPanel buildRegisterPanel() {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        JLabel h1 = leftLabel("Create your account", FONT_H1, INK);
        JLabel sub = leftLabel("Sign up and get fresh groceries in minutes", FONT_SUB, MUTED);
        sub.setBorder(new EmptyBorder(4, 0, 20, 0));

        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField pwField = new JPasswordField();
        JPasswordField confirmField = new JPasswordField();

        JButton showPwBtn = smallLinkButton("Show");
        JButton showConfirmBtn = smallLinkButton("Show");
        showPwBtn.addActionListener(e -> togglePasswordVisibility(pwField, showPwBtn));
        showConfirmBtn.addActionListener(e -> togglePasswordVisibility(confirmField, showConfirmBtn));

        JButton registerBtn = primaryButton("Create Account");
        registerBtn.addActionListener(e -> doRegister(
                nameField.getText().trim(),
                phoneField.getText().trim(),
                emailField.getText().trim(),
                new String(pwField.getPassword()).trim(),
                new String(confirmField.getPassword()).trim()
        ));

        p.add(h1);
        p.add(sub);
        p.add(fieldBlock("Full Name", nameField, null));
        p.add(Box.createVerticalStrut(12));
        p.add(fieldBlock("Mobile Number", phoneField, null));
        p.add(Box.createVerticalStrut(12));
        p.add(fieldBlock("Email Address", emailField, null));
        p.add(Box.createVerticalStrut(12));
        p.add(fieldBlock("Password", pwField, showPwBtn));
        p.add(Box.createVerticalStrut(12));
        p.add(fieldBlock("Confirm Password", confirmField, showConfirmBtn));
        p.add(Box.createVerticalStrut(6));
        p.add(registerBtn);

        return p;
    }

    // =========================================================
    //  Reusable UI builders
    // =========================================================
    private JLabel leftLabel(String text, Font font, Color color) {
        JLabel l = new JLabel(text);
        l.setFont(font);
        l.setForeground(color);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private JPanel fieldBlock(String labelText, JComponent field, JButton trailingBtn) {
        JPanel block = new JPanel();
        block.setOpaque(false);
        block.setLayout(new BoxLayout(block, BoxLayout.Y_AXIS));
        block.setAlignmentX(Component.LEFT_ALIGNMENT);
        block.setMaximumSize(new Dimension(360, 62));

        JLabel lbl = new JLabel(labelText.toUpperCase());
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(LEAF_GREEN);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        lbl.setBorder(new EmptyBorder(0, 0, 6, 0));

        field.setFont(FONT_INPUT);
        field.setBorder(new CompoundBorder(
                new RoundedLineBorder(FIELD_BORDER, 8, 1),
                new EmptyBorder(10, 12, 10, 12)));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setPreferredSize(new Dimension(360, 42));
        field.setMaximumSize(new Dimension(360, 42));

        block.add(lbl);

        if (trailingBtn != null) {
            JLayeredPane layered = new JLayeredPane();
            layered.setAlignmentX(Component.LEFT_ALIGNMENT);
            layered.setPreferredSize(new Dimension(360, 42));
            layered.setMaximumSize(new Dimension(360, 42));
            field.setBounds(0, 0, 360, 42);
            trailingBtn.setBounds(300, 8, 50, 26);
            trailingBtn.setFont(new Font("SansSerif", Font.BOLD, 11));
            layered.add(field, Integer.valueOf(0));
            layered.add(trailingBtn, Integer.valueOf(1));
            block.add(layered);
        } else {
            block.add(field);
        }

        return block;
    }

    private JButton smallLinkButton(String text) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.BOLD, 11));
        b.setForeground(new Color(0x2F, 0x7A, 0x72));
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }

    private JButton primaryButton(String text) {
        JButton b = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? LEAF_DARK : LEAF_GREEN);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        b.setFont(FONT_BTN);
        b.setForeground(CREAM);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        b.setMaximumSize(new Dimension(360, 46));
        b.setPreferredSize(new Dimension(360, 46));
        return b;
    }

    private JButton outlineButton(String text) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
        b.setForeground(INK);
        b.setBackground(Color.WHITE);
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setBorder(new CompoundBorder(
                new RoundedLineBorder(FIELD_BORDER, 8, 1),
                new EmptyBorder(8, 10, 8, 10)));
        return b;
    }

    private void togglePasswordVisibility(JPasswordField field, JButton btn) {
        boolean hidden = field.getEchoChar() != 0;
        field.setEchoChar(hidden ? (char) 0 : '•');
        btn.setText(hidden ? "Hide" : "Show");
    }

    // =========================================================
    //  Actions
    // =========================================================
    private void doLogin() {
        String id = loginIdField.getText().trim();
        String pw = new String(loginPwField.getPassword()).trim();

        if (id.isEmpty() || pw.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both fields.",
                    "Missing information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // TODO: replace with real authentication (DB / API call)
        rootCardLayout.show(rootCardPanel, "WELCOME");
    }

    private void doRegister(String name, String phone, String email, String pw, String confirm) {
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || pw.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.",
                    "Missing information", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!pw.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.",
                    "Password mismatch", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // TODO: persist the new user (DB / API call)
        JOptionPane.showMessageDialog(this, "Account created successfully. Please login.",
                "Welcome to Vinoth Mart", JOptionPane.INFORMATION_MESSAGE);
        switchTab(true);
    }

    // =========================================================
    //  Simple post-login welcome screen (placeholder for the shop)
    // =========================================================
    private JPanel buildWelcomeScreen() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(CREAM);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(LEAF_GREEN);
        header.setBorder(new EmptyBorder(18, 30, 18, 30));
        JLabel logo = new JLabel("<html>Vinoth<span style='color:#FF7A3D;'>Mart</span></html>");
        logo.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 22));
        logo.setForeground(CREAM);
        JButton logoutBtn = outlineButton("Logout");
        logoutBtn.addActionListener(e -> {
            loginIdField.setText("");
            loginPwField.setText("");
            rootCardLayout.show(rootCardPanel, "AUTH");
        });
        header.add(logo, BorderLayout.WEST);
        header.add(logoutBtn, BorderLayout.EAST);

        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(CREAM);
        JLabel msg = new JLabel("<html><div style='text-align:center;'>"
                + "<span style='font-size:22px;color:#1E5F3A;'>Login successful 🎉</span><br><br>"
                + "<span style='font-size:13px;color:#6E7A70;'>This is where the Vinoth Mart shop page<br>would load next.</span>"
                + "</div></html>");
        center.add(msg);

        p.add(header, BorderLayout.NORTH);
        p.add(center, BorderLayout.CENTER);
        return p;
    }

    // =========================================================
    //  Small helper: rounded line border
    // =========================================================
    static class RoundedLineBorder extends LineBorder {
        private final int radius;

        RoundedLineBorder(Color color, int radius, int thickness) {
            super(color, thickness, true);
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getLineColor());
            g2.setStroke(new BasicStroke(getThickness()));
            g2.draw(new RoundRectangle2D.Float(x + 1, y + 1, width - 2, height - 2, radius, radius));
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        SwingUtilities.invokeLater(() -> new VinothMartLogin().setVisible(true));
    }
}