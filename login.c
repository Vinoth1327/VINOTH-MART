#include <QApplication>
#include <QWidget>
#include <QMainWindow>
#include <QStackedWidget>
#include <QTabWidget>
#include <QLineEdit>
#include <QPushButton>
#include <QLabel>
#include <QVBoxLayout>
#include <QHBoxLayout>
#include <QGridLayout>
#include <QMessageBox>
#include <QFrame>
#include <QSpacerItem>
#include <QSizePolicy>

// ============================================================
// VinothMartLogin
// C++ Qt conversion of the Java Swing Vinoth Mart application
// ============================================================

class VinothMartLogin : public QMainWindow
{
public:

    // --------------------------------------------------------
    // Theme colors
    // --------------------------------------------------------
    QString LEAF_GREEN = "#1E5F3A";
    QString LEAF_DARK  = "#123D24";
    QString SUNSET     = "#FF7A3D";
    QString CREAM      = "#FBF8F1";
    QString INK        = "#1F241E";
    QString MUTED      = "#6E7A70";
    QString FIELD_BORDER = "#D8E0D6";

    QStackedWidget *rootStack;

    QLineEdit *loginIdField;
    QLineEdit *loginPwField;

    QTabWidget *tabs;

    VinothMartLogin()
    {
        setWindowTitle("Vinoth Mart | Login");
        resize(1000, 620);
        setMinimumSize(760, 560);

        rootStack = new QStackedWidget();

        rootStack->addWidget(createAuthScreen());
        rootStack->addWidget(createWelcomeScreen());

        setCentralWidget(rootStack);
        rootStack->setCurrentIndex(0);
    }

    // ========================================================
    // AUTH SCREEN
    // ========================================================

    QWidget* createAuthScreen()
    {
        QWidget *auth = new QWidget();

        QHBoxLayout *mainLayout = new QHBoxLayout(auth);
        mainLayout->setContentsMargins(0, 0, 0, 0);
        mainLayout->setSpacing(0);

        mainLayout->addWidget(createBrandPanel(), 48);
        mainLayout->addWidget(createFormPanel(), 52);

        auth->setStyleSheet(
            "background-color: " + CREAM + ";"
        );

        return auth;
    }

    // ========================================================
    // LEFT BRAND PANEL
    // ========================================================

    QWidget* createBrandPanel()
    {
        QFrame *brand = new QFrame();

        brand->setStyleSheet(
            "QFrame {"
            "background-color: " + LEAF_GREEN + ";"
            "}"
        );

        QVBoxLayout *layout = new QVBoxLayout(brand);
        layout->setContentsMargins(44, 45, 44, 35);

        // Logo
        QLabel *logo = new QLabel(
            "<span style='color:#FBF8F1;'>Vinoth</span>"
            "<span style='color:#FF7A3D;'>Mart</span>"
        );

        logo->setStyleSheet(
            "font-family: Georgia;"
            "font-size: 30px;"
            "font-weight: bold;"
        );

        QLabel *tag = new QLabel(
            "INSTAMART · GROCERIES IN MINUTES"
        );

        tag->setStyleSheet(
            "color: rgba(255,255,255,160);"
            "font-size: 11px;"
        );

        layout->addWidget(logo);
        layout->addWidget(tag);

        layout->addStretch();

        // Headline
        QLabel *headline = new QLabel(
            "Fresh groceries,<br>"
            "delivered to your<br>"
            "doorstep in 15 minutes."
        );

        headline->setStyleSheet(
            "color: #FBF8F1;"
            "font-family: Georgia;"
            "font-size: 26px;"
        );

        QLabel *description = new QLabel(
            "Join thousands of happy customers shopping "
            "smarter with Vinoth Mart every single day."
        );

        description->setWordWrap(true);

        description->setStyleSheet(
            "color: rgba(255,255,255,190);"
            "font-size: 13px;"
        );

        layout->addWidget(headline);
        layout->addSpacing(14);
        layout->addWidget(description);

        layout->addSpacing(30);

        // Stats
        QHBoxLayout *stats = new QHBoxLayout();

        stats->addWidget(createStat("15 min", "Avg. delivery"));
        stats->addWidget(createStat("2,000+", "Products"));
        stats->addWidget(createStat("50k+", "Happy users"));

        layout->addLayout(stats);

        layout->addStretch();

        QLabel *bottom = new QLabel(
            "© 2026 Vinoth Mart  •  Privacy  •  Terms"
        );

        bottom->setStyleSheet(
            "color: rgba(255,255,255,130);"
            "font-size: 11px;"
        );

        layout->addWidget(bottom);

        return brand;
    }

    // ========================================================
    // STAT BLOCK
    // ========================================================

    QWidget* createStat(QString number, QString text)
    {
        QWidget *widget = new QWidget();

        QVBoxLayout *layout = new QVBoxLayout(widget);
        layout->setContentsMargins(0, 0, 20, 0);
        layout->setSpacing(2);

        QLabel *num = new QLabel(number);

        num->setStyleSheet(
            "color: #FF7A3D;"
            "font-family: Georgia;"
            "font-size: 20px;"
            "font-weight: bold;"
        );

        QLabel *lbl = new QLabel(text);

        lbl->setStyleSheet(
            "color: rgba(255,255,255,160);"
            "font-size: 11px;"
        );

        layout->addWidget(num);
        layout->addWidget(lbl);

        return widget;
    }

    // ========================================================
    // RIGHT FORM PANEL
    // ========================================================

    QWidget* createFormPanel()
    {
        QWidget *form = new QWidget();

        form->setStyleSheet(
            "background-color: " + CREAM + ";"
        );

        QVBoxLayout *layout = new QVBoxLayout(form);

        layout->setContentsMargins(60, 60, 60, 60);

        tabs = new QTabWidget();

        tabs->setStyleSheet(
            "QTabWidget::pane {"
            "border: none;"
            "}"

            "QTabBar::tab {"
            "background: #EEF1EC;"
            "color: #6E7A70;"
            "padding: 12px 55px;"
            "font-weight: bold;"
            "}"

            "QTabBar::tab:selected {"
            "background: #1E5F3A;"
            "color: #FBF8F1;"
            "}"
        );

        tabs->addTab(createLoginPanel(), "Login");
        tabs->addTab(createRegisterPanel(), "Register");

        layout->addWidget(tabs);

        return form;
    }

    // ========================================================
    // LOGIN PANEL
    // ========================================================

    QWidget* createLoginPanel()
    {
        QWidget *panel = new QWidget();

        QVBoxLayout *layout = new QVBoxLayout(panel);

        QLabel *title = new QLabel("Welcome back");

        title->setStyleSheet(
            "font-family: Georgia;"
            "font-size: 24px;"
            "font-weight: bold;"
            "color: #1F241E;"
        );

        QLabel *subtitle = new QLabel(
            "Login to continue shopping with Vinoth Mart"
        );

        subtitle->setStyleSheet(
            "color: #6E7A70;"
            "font-size: 13px;"
        );

        layout->addWidget(title);
        layout->addWidget(subtitle);
        layout->addSpacing(20);

        // Email / mobile
        QLabel *idLabel = new QLabel("EMAIL OR MOBILE NUMBER");

        idLabel->setStyleSheet(
            "color: #1E5F3A;"
            "font-weight: bold;"
            "font-size: 11px;"
        );

        loginIdField = new QLineEdit();

        loginIdField->setPlaceholderText(
            "Enter email or mobile number"
        );

        styleField(loginIdField);

        layout->addWidget(idLabel);
        layout->addWidget(loginIdField);
        layout->addSpacing(14);

        // Password
        QLabel *pwLabel = new QLabel("PASSWORD");

        pwLabel->setStyleSheet(
            "color: #1E5F3A;"
            "font-weight: bold;"
            "font-size: 11px;"
        );

        QHBoxLayout *passwordLayout = new QHBoxLayout();

        loginPwField = new QLineEdit();
        loginPwField->setEchoMode(QLineEdit::Password);

        loginPwField->setPlaceholderText("Enter password");

        styleField(loginPwField);

        QPushButton *showPassword =
            new QPushButton("Show");

        styleSmallButton(showPassword);

        connect(showPassword, &QPushButton::clicked,
                this, [this, showPassword]()
        {
            if(loginPwField->echoMode() ==
               QLineEdit::Password)
            {
                loginPwField->setEchoMode(
                    QLineEdit::Normal
                );

                showPassword->setText("Hide");
            }
            else
            {
                loginPwField->setEchoMode(
                    QLineEdit::Password
                );

                showPassword->setText("Show");
            }
        });

        passwordLayout->addWidget(loginPwField);
        passwordLayout->addWidget(showPassword);

        layout->addWidget(pwLabel);
        layout->addLayout(passwordLayout);

        // Forgot password
        QHBoxLayout *forgotLayout =
            new QHBoxLayout();

        forgotLayout->addStretch();

        QPushButton *forgot =
            new QPushButton("Forgot password?");

        styleSmallButton(forgot);

        forgotLayout->addWidget(forgot);

        layout->addLayout(forgotLayout);

        layout->addSpacing(10);

        // Login button
        QPushButton *login =
            new QPushButton("Login");

        stylePrimaryButton(login);

        connect(login, &QPushButton::clicked,
                this, [this]()
        {
            doLogin();
        });

        layout->addWidget(login);

        layout->addSpacing(15);

        QLabel *orLabel =
            new QLabel("or continue with");

        orLabel->setAlignment(Qt::AlignCenter);

        orLabel->setStyleSheet(
            "color: #6E7A70;"
            "font-size: 12px;"
        );

        layout->addWidget(orLabel);

        layout->addSpacing(10);

        QHBoxLayout *social =
            new QHBoxLayout();

        QPushButton *google =
            new QPushButton("Google");

        QPushButton *otp =
            new QPushButton("OTP Login");

        styleOutline