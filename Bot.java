import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

        String start = update.getMessage().getText();
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setChatId(update.getMessage().getChatId().toString());

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("Кенгуру"));
        keyboardRow.add(new KeyboardButton("Ленивец"));

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("Лев"));
        keyboardRow1.add(new KeyboardButton("Обезьяна"));
        keyboardRow1.add(new KeyboardButton("Тигр"));

        List<KeyboardRow> list = new ArrayList<>();
        list.add(keyboardRow);
        list.add(keyboardRow1);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(list);
        sendMessage1.setText(update.getMessage().getText());
        sendMessage1.setReplyMarkup(replyKeyboardMarkup);

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(update.getMessage().getChatId().toString());
        InputFile inputFile;
        File image = null;
        URL url = null;
        try {
            if(start.equals("/start")){
                sendMessage1.setText("Выберите животное.");
                execute(sendMessage1);
                return;
            }
            sendMessage1.setText("Выберите животное.");
            switch (update.getMessage().getText()){
                case "Кенгуру":
                    url = getClass().getClassLoader().getResource("кенгуру.jpg");
                    break;
                case "Обезьяна":
                    url =getClass().getClassLoader().getResource("обезьяна.jpg");
                    break;
                case "Лев":
                    url = getClass().getClassLoader().getResource("лев.jpg");
                    break;
                case "Тигр":
                    url = getClass().getClassLoader().getResource("тигр.jpg");
                    break;
                case "Ленивец":
                    url = getClass().getClassLoader().getResource("ленивец.jpg");
                    break;
            }
            image = Paths.get(url.toURI()).toFile();
            inputFile = new InputFile(image);
            sendPhoto.setPhoto(inputFile);
            execute(sendPhoto);            
        } catch (URISyntaxException | TelegramApiException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    @Override
    public String getBotUsername() {
        return "an1ma1_bot";
    }

    @Override
    public String getBotToken() {
        return "BotToken";
    }
}
