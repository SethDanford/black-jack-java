package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardPanel extends JPanel {
	private JPanel innerPanel = new JPanel();
	private int cardWidth;
	private int cardHeight;
	// Dictates whether or not the cards are displayed by addCards.
	private Boolean showCards = true;
	// Getting and storing the screen dimensions.
	private final static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	/**
	 *  The outer cardPanel is only responsible for holding the innerPanel which is the panel
	 *  that actually displays the cards
	 * @param width
	 * @param height
	 */
	public CardPanel(int width, int height) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		innerPanel = new JPanel();
		setPreferredSize(new Dimension(width, height));
		add(this.innerPanel);
		innerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		cardWidth = (int) (screen.getWidth()*2/3)/6;
		cardHeight = (int) (cardWidth * 1.5);
	}
	// Used for adding NEW cards to the panel.
	public void addCard(String card) {
		if (showCards == true) {
			JLabel lblNewLabel = new JLabel();
			lblNewLabel.setBounds(10, 11, cardWidth, cardHeight);
			innerPanel.add(lblNewLabel);
			ImageIcon myIcon2 = new ImageIcon(card);
			ImageIcon image = this.scaleImageIcon(lblNewLabel, myIcon2);
			lblNewLabel.setIcon(image);
		}
	}
	// Used for adding cards the players have already drawn to the panel.
	public void updateCardPanel(String card) { // TODO: THIS IS OBSOLETE
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(10, 11, cardWidth, cardHeight);
		innerPanel.add(lblNewLabel);
		ImageIcon myIcon2 = new ImageIcon(card);
		ImageIcon image = this.scaleImageIcon(lblNewLabel, myIcon2);
		lblNewLabel.setIcon(image);
	}
	// Used for clearing the panel that holds the cards.
	public void removeCards() {
		innerPanel.removeAll();
		innerPanel.revalidate();
		innerPanel.repaint();
	}
	
	// Used for scaling the the images.
	public ImageIcon scaleImageIcon(JLabel label, ImageIcon imgIcn) {
		Image img = imgIcn.getImage();
		Image newImage = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImage);
		return image;
	}
	
	public int getCardWidth() {
		return cardWidth;
	}
	public int getCardHeight() {
		return cardHeight;
	}
	public void setShowCards(Boolean showOrNot) {
		showCards = showOrNot;
	}
}
