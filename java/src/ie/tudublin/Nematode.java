package ie.tudublin;

import processing.data.TableRow;
import javazoom.jl.player.PlayerApplet;
import processing.core.PApplet;

public class Nematode
{
    private String name;
    private float length;
    private boolean limbs;
    private String gender;
    private boolean eyes;

    public Nematode(String name, float length, boolean limbs, String string, boolean eyes)
    {
        this.name = name;
        this.length = length;
        this.limbs = limbs;
        this.gender = string;
        this.eyes = eyes;
    }

    public String toString()
    {
        return "Nematode [eyes=" + eyes + ", gender=" + gender + ", length=" + length + ", limbs=" + limbs + ", name="
                + name + "]";
    }

    public Nematode(TableRow tr)
    {
        this(
            tr.getString("name"), 
            tr.getFloat("length"),
            tr.getInt("limbs") == 1,  
            tr.getString("gender"),
            tr.getInt("eyes") == 1
        );
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public float getLength()
    {
        return length;
    }

    public void setLength(float length)
    {
        this.length = length;
    }

    public boolean isLimbs()
    {
        return limbs;
    }

    public void setLimbs(boolean limbs)
    {
        this.limbs = limbs;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public boolean isEyes()
    {
        return eyes;
    }

    public void setEyes(boolean eyes)
    {
        this.eyes = eyes;
    }

    public void render(NematodeVisualiser nemaV, float c)
    {
        nemaV.background(0);
        int nemaSize = 45;
        int sizeOfText = 20;

        nemaV.fill(255);
        
        nemaV.textSize(sizeOfText);
        nemaV.textAlign(PApplet.CENTER, PApplet.CENTER);
        nemaV.text(name, nemaV.width/2, (nemaV.height/2 - (length/2 * nemaSize) - (sizeOfText * 3)));

        nemaV.strokeWeight(2);
        nemaV.stroke(255);
        nemaV.noFill();

        for(int circles = 0; circles < length; circles++)
        {
            nemaV.stroke(c, 255, 255);
            nemaV.strokeWeight(5);
            nemaV.pushMatrix();
            nemaV.translate(nemaV.width/2, (nemaV.height/2 - (length/2 * nemaSize)) + (nemaSize * circles));
            nemaV.circle(0, 0, nemaSize);

            if (length - 1 == circles)
            {
                switch (gender)
                {
                    case "m":
                    {
                        nemaV.line(0, nemaSize/2, 0, nemaSize);
                        nemaV.circle(0, nemaSize + 5, 10);
                        break;
                    }
                    
                    case "h":
                    {
                        nemaV.circle(0, 0, nemaSize/2);

                        nemaV.line(0, nemaSize/2, 0, nemaSize);
                        nemaV.circle(0, nemaSize + 5, 10);
                        break;
                    }

                    case "f":
                    {
                        nemaV.circle(0, 0, nemaSize/2);
                        break;
                    }
                }
            }

            if (limbs)
            {
                nemaV.line(nemaSize/2, 0, nemaSize, 0);
                nemaV.line(-nemaSize, 0, -nemaSize/2, 0);
            }

            nemaV.popMatrix();
        }

        
    }
}