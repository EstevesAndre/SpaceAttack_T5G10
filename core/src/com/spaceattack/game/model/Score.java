package com.spaceattack.game.model;

/**
 * Score of previous played game
 */
public class Score implements Comparable<Score>{

      /**
     * The score achieved
     */
    private int score;

    /**
     * Date when the score was achieved
     */
    private String date;

    /**
     * Score and Date of the current game
     *
     * @param score Score achieved
     * @param date Date of the current game1
     */
    public Score(int score, String date){
        this.score = score;
        this.date = date;
    }

    /**
     * Gets the score achieved
     *
     * @return The score achieved
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the date
     *
     * @return The date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the score
     *
     * @param score The score achieved
     */
    public void setScore(int score) { this.score = score; }

    /**
     * Sets the date
     *
     * @param date The date
     */
    public void setDate(String date) { this.date = date; }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Score s) {
        //sorts in reverse
        if(score < s.getScore())
            return 1;
        else if (score > s.getScore())
            return -1;

        return 0;
    }

}

