package org.minecraft.graphics.font.mesh;

import java.util.ArrayList;
import java.util.List;

/**
 * During the loading of a text this represents one word in the text.
 * <li>Edited by 4347
 *
 * @author Karl
 */
public final class Word {

	/**
	 * The characters in the word
	 */
    private final List<Character> characters = new ArrayList<>();

	/**
	 * The width of the word
	 */
	private double width = 0;

	/**
	 * The font size of the word
	 */
	private final double fontSize;

    /**
     * Create a new empty word.
     *
     * @param fontSize the font size of the text which this word is in.
     */
    protected Word(double fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * Adds a character to the end of the current word and increases the screen-space width of the word.
     *
     * @param character the character to be added.
     */
    protected void addCharacter(Character character) {
        characters.add(character);
        width += character.getxAdvance() * fontSize;
    }

    /**
	 * Returns the list of characters in the word.
	 *
     * @return The list of characters in the word.
	 * @author 4347
     */
    protected List<Character> getCharacters() {
        return characters;
    }

    /**
	 * Returns the width of the word in terms of screen size
	 *
     * @return The width of the word in terms of screen size.
     */
    protected double getWordWidth() {
        return width;
    }

}
