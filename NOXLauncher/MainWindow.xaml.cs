using System.Windows;

namespace NOXLauncher
{
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void PlayButton_Click(object sender, RoutedEventArgs e)
        {
            string nickname = NicknameBox.Text.Trim();

            if (string.IsNullOrWhiteSpace(nickname))
            {
                MessageBox.Show("Введите игровой ник!");
                return;
            }

            MessageBox.Show(
                $"Добро пожаловать, {nickname}!",
                "NØX Launcher"
            );
        }
    }
}
