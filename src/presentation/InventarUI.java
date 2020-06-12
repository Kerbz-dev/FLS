package presentation;

import java.text.DecimalFormat;
import java.text.ParsePosition;

import entity.Biler;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.GetBiler;
import logic.getKunde;
import logic.opretLaan;
public class InventarUI {

		private DecimalFormat numberFormatter = new DecimalFormat("0");
		private TextField bilIDTF, bilnavnTF, bilprisTF, inventarTF, SoegTField;
		private Button opdaterInventarBtn;
		private Stage opretInventarStage;
		private String bilIDGetText, bilnavnGetText, bilprisGetText, inventarGetText, bilnavn;
		private Label opretStatusLbl;
		private Scene scene;
		private int bilid, bilpris, bilinventar;
		private Pane pane;
		private Image ferrari;
		private ImageView ferraripic;
		private ObservableList<Biler> bilList;
		private TableView<Biler> bilTable = new TableView<Biler>();
		private GetBiler billgc = new GetBiler();

		@SuppressWarnings("unchecked")
		public void start() {
			opretInventarStage = new Stage();
			opretInventarStage.setTitle("Ferrari lånesystem");
			opretInventarStage.getIcons()
					.add(new Image("https://i.pinimg.com/564x/c9/87/c8/c987c8a5c896fca22c5cfbd62edb7359.jpg"));

			// Sætter pane + billede
			pane = new Pane();
			ferrari = new Image(
					"https://3.bp.blogspot.com/-DRM75enaO7s/VDrpAiCm55I/AAAAAAAABGM/VnsBvuXIygU/s1600/Ferrari%2BCar%2Blogos.jpg%22");
			pane.setPrefHeight(670.0);
			ferraripic = new ImageView();

			// Sætter knap
			opdaterInventarBtn = new Button("Tilføj bil til bilinventar");

			// Sætter TField
			bilIDTF = new TextField();
			bilnavnTF = new TextField();
			inventarTF = new TextField();
			bilprisTF = new TextField();
			SoegTField = new TextField();
			opretStatusLbl = new Label();
			bilIDTF.setEditable(false);
			bilnavnTF.setEditable(false);
			inventarTF.setEditable(false);
			bilprisTF.setEditable(false);
			opretInventarStage.setResizable(arg0);

			// TField lokationer
			bilIDTF.relocate(350, 195.0);
			bilIDTF.setPrefHeight(25.0);
			bilIDTF.setPrefWidth(321);
			bilnavnTF.relocate(350, 225.0);
			bilnavnTF.setPrefHeight(25.0);
			bilnavnTF.setPrefWidth(321.0);
			inventarTF.relocate(350, 255.0);
			inventarTF.setPrefHeight(25.0);
			inventarTF.setPrefWidth(321.0);
			bilprisTF.relocate(350, 285.0);
			bilprisTF.setPrefHeight(25.0);
			bilprisTF.setPrefWidth(321.0);
			opdaterInventarBtn.relocate(350, 350);
			bilTable.setPrefWidth(250);
			bilTable.setPrefHeight(500);
			bilTable.relocate(23, 75);
			SoegTField.setLayoutX(23);
			SoegTField.setLayoutY(25);
			SoegTField.setPrefHeight(35);
			SoegTField.setPrefWidth(250);
			SoegTField.setPromptText("Søg - fx tlf. nr.");
			SoegTField.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");

			ferraripic.setFitWidth(350);
			ferraripic.setFitHeight(130);
			ferraripic.setImage(ferrari);
			ferraripic.relocate(305, 20);

			// Sætter prompt text til kun at blive vist når et bogstav bliver indsat
			
			bilprisTF.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");
			inventarTF.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");
	

	
			bilIDTF.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");
			bilnavnTF.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");
			
			// Sætter pane baggrundsfarve
			pane.setStyle("-fx-background-color: #F40808");

			// Sætter prompt text
			bilIDTF.setPromptText("Fornavn");
			bilnavnTF.setPromptText("Efternavn");
		
			bilprisTF.setPromptText("Tlf.nr");
			inventarTF.setPromptText("Postnr");
			

			bilprisTF.setEditable(false);

			// Laver tabel + funktionalitet
			TableColumn<Biler, String> ColumnBilnavn = new TableColumn<Biler, String>("Bilnavn");
			TableColumn<Biler, String> ColumnBilpris = new TableColumn<Biler, String>("Bilpris");
			TableColumn<Biler, String> ColumnBilinventar = new TableColumn<Biler, String>("Inventar");


			ColumnBilnavn.setCellValueFactory(e -> {
				Biler blr = e.getValue();
				bilnavn = blr.getBilnavn();

				return new SimpleStringProperty(bilnavn);
			});

			ColumnBilpris.setCellValueFactory(e -> {
				Biler blr = e.getValue();
				bilpris = blr.getBilPris();
				String bilprisString = Integer.toString(bilpris);
				return new SimpleStringProperty(bilprisString);
			});

			ColumnBilinventar.setCellValueFactory(e -> {
				Biler blr = e.getValue();
				bilinventar = blr.getInventar();
				String bilinventarString = Integer.toString(bilinventar);
				return new SimpleStringProperty(bilinventarString);
			});

			bilTable.getColumns().addAll(ColumnBilnavn, ColumnBilpris, ColumnBilinventar);
			bilTable.setItems(bilList);
			udfyldTbl();

			// Sætter TField til kun at tal kan sættes


			bilprisTF.setTextFormatter(new TextFormatter<>(c -> {
				if (c.getControlNewText().isEmpty()) {
					return c;
				}
				ParsePosition parsePosition = new ParsePosition(0);
				Object object = numberFormatter.parse(c.getControlNewText(), parsePosition);

				if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
					return null;
				} else {
					return c;
				}
			}));

			// Sætter font størrelser
			SoegTField.setFont(new Font(18));
			opretStatusLbl.setFont(new Font(24));
			bilnavnTF.setFont(new Font(14));
			bilIDTF.setFont(new Font(14));
			opdaterInventarBtn.setFont(new Font(24));
			bilprisTF.setFont(new Font(14));
			inventarTF.setFont(new Font(14));
	

			// Tilføj til pane
			pane.getChildren().addAll(bilIDTF, bilnavnTF, inventarTF, bilprisTF, opdaterInventarBtn, opretStatusLbl, ferraripic,
					SoegTField, bilTable);

			// Vis scene
			scene = new Scene(pane, 950, 670);
			opretInventarStage.setScene(scene);
			opretInventarStage.show();

			// Sætter set on action på knap
		//	opdaterInventarBtn.setOnAction(e -> checkOpdatering());

			// Sætter mouseevent på tabel
			opdaterInventarBtn.setOnAction(e -> inventarUpdate(bilid, bilinventar));
			bilTable.setOnMouseClicked((MouseEvent event) -> {
				if (event.getClickCount() > 0) {
					setTFields();
				}
			});
		}

		// Metoder
		

		private void opdaterInventarSuccess() {

			opretStatusLbl.setText("Kunde opdateret!");
			opretStatusLbl.setTextFill(Color.LIGHTGREEN);
			opretStatusLbl.relocate(390.5, 490);
			
		}
		 public void inventarUpdate(int bilid, int bilinventar) {
			 if (bilTable.getSelectionModel().getSelectedItem() != null) {
					Biler selectedBil = bilTable.getSelectionModel().getSelectedItem();
					bilid = selectedBil.getBilId();
			 opretLaan oprLaanDB = new opretLaan();
			 System.out.println("Gamle bilinventar var: " + bilinventar);
			 bilinventar +=1;
			 
			 System.out.println("Ny bilinventar er: " + bilinventar);
		        oprLaanDB.inventarUpdate(bilid, bilinventar);
		        opdaterInventarSuccess();
			 }
		    }

		private boolean fieldsEmpty() {
			bilIDGetText = bilIDTF.getText();
			bilnavnGetText = bilnavnTF.getText();
			bilprisGetText = bilprisTF.getText();
			inventarGetText = inventarTF.getText();
			
			if (bilIDGetText.isEmpty() || bilnavnGetText.isEmpty() || bilprisGetText.isEmpty() || inventarGetText.isEmpty()) {
				return true;
			}
			return false;
		}

		private void opdaterFejl() {
			opretStatusLbl.setText("Udfyld venligst alle felter!");
			opretStatusLbl.setTextFill(Color.WHITE);
			opretStatusLbl.relocate(345, 490);

		}


		private void setTFields() {
			if (bilTable.getSelectionModel().getSelectedItem() != null) {
				Biler selectedBil = bilTable.getSelectionModel().getSelectedItem();
				bilid = selectedBil.getBilId();
				bilnavn = selectedBil.getBilnavn();
				bilpris = selectedBil.getBilPris();
				bilinventar = selectedBil.getInventar();
				String inventarString = Integer.toString(bilinventar);
				String bilprisString = Integer.toString(bilpris);
				String bilidString = Integer.toString(bilid);
				
				bilIDTF.setText(bilidString);
				bilnavnTF.setText(bilnavn);
				bilprisTF.setText(bilprisString);
				inventarTF.setText(inventarString);
			}
		}

		private void opdaterTable() {
			if (bilList.size() > 0) {
				bilList.clear();
				udfyldTbl();
			}
		}
	
		private void udfyldTbl() {
			bilTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			bilList = FXCollections.observableList(billgc.getAllBilerInfo());
			FilteredList<Biler> filteredData = new FilteredList<>(bilList, p -> true);

			// Søg funktion
			SoegTField.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(formSearch -> {
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}

					String lowerCaseFilter = newValue.toLowerCase();

					if (formSearch.getBilnavn().toLowerCase().contains(lowerCaseFilter)) {
						return true;
					}
					return false;
				});
			});

			SortedList<Biler> sortedData = new SortedList<>(filteredData);
			sortedData.comparatorProperty().bind(bilTable.comparatorProperty());

			// Tilføjer sorteret og filtreret data til vores TableView
			bilTable.setItems(sortedData);
		}
	}

