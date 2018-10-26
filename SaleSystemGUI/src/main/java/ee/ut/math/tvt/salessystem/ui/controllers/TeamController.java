package ee.ut.math.tvt.salessystem.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class TeamController implements Initializable {
    @FXML
    private Label getTeamName;

    @FXML
    private Label getContactPerson;

    @FXML
    private Label getMembers;

    @FXML
    private ImageView getTeamImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applicationData();
    }

    @FXML
    public void applicationData() {
        try (FileReader reader = new FileReader(System.getProperty("user.dir")+ File.separator +"src" + File.separator + "main" + File.separator +"resources" + File.separator +"application.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            String teamName = properties.getProperty("team_name");
            getTeamName.setText(teamName);
            String teamContactPerson = properties.getProperty("team_contact_person");
            getContactPerson.setText(teamContactPerson);
            String teamMembers = properties.getProperty("team_members");
            getMembers.setText(teamMembers);

            //team logo
            String imageURL = properties.getProperty("team_logo");
            Image image = new Image(imageURL);
            getTeamImage.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
