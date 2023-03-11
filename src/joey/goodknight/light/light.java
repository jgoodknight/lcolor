package joey.goodknight.light;



/**
 *
 * @author workhorse
 */
public class light extends Object{

    private float WAVELENGTH;
    private float FREQUENCY;
    //private float POWER;

    //private boolean VISIBLE;
    //private boolean IR;
    //private boolean UV;

    public static final int UNITS_NM = 1;
    public static final int UNITS_HZ = 2;
    public static final int UNITS_EV = 3;
    public static final int UNITS_J = 4;
    public static final int UNITS_INVCM = 5;

    private final float SPEED = 299792458;
    //wavelength defined in terms of meters and frequency in terms of Hz and Power in Watts

    public light() {
        //EL NADA
    }

    //for integer wavelengths
    public light(int wl) {
        this.setWL((float)wl * (float)1e-9);
    }

    public light (float parameter, int unit) {
        switch(unit) {
            case 1: /*nm*/
                this.setWL(parameter * (float)1e-9);
                break;
            case 2: /*Hz*/
                this.setF(parameter);
                break;
            case 3: /*eV*/
                this.setWL((float)1.239841e-6 / parameter);
                break;
            case 4: /*J*/
                this.setWL((float)1.98645e-25 / parameter);
                break;
            case 5: /*invcm*/
                this.setWL((float)1e-2 / parameter);
                break;
            default: this.setWL(parameter * (float)1e-9);
        }
    }

    public void setWL(float wl) {
        //code to set wavelength and adjust frequency
        WAVELENGTH = wl;
        FREQUENCY = SPEED / wl;
    }

    public void setF(float f) {
        //code to adjust frequency and correspconding wavelength
        WAVELENGTH = SPEED / f;
        FREQUENCY = f;
    }

    //public void setP(float p) {
        //POWER = p;
   // }
    
    public float getFreq() {
    	return FREQUENCY;
    }
    
    public float getWL() {
    	return WAVELENGTH;
    }
    
    public float getParameter(int unit) {
        switch(unit) {
            case 1: /*nm*/
                return (this.WAVELENGTH * (float)1e9);
                
            case 2: /*Hz*/
                return this.FREQUENCY;
                
            case 3: /*eV*/
            	return (float)1.239841e-6 / this.WAVELENGTH;
                
            case 4: /*J*/
            	return (float)1.98645e-25 / this.WAVELENGTH;
                
            case 5: /*invcm*/
            	return (float)1e-2 / this.WAVELENGTH;
                
            default: return (float)0;
        }
    }
    
    

    public int redValue() {
        float wl = WAVELENGTH * (float)1e9;
        float red = 0;

        //TODO: add intensity corrections for edge of visibility
        if ( 380 < wl && wl <= 440) {
            red = -(wl -440)/(440 - 380);
        } else if ( 440 < wl && wl <= 490 ) {
            red = 0;
        } else if ( 490 < wl && wl <= 510 ) {
            red = 0;
        } else if ( 510 < wl && wl <= 580 ) {
            red = (wl - 510)/(580 - 510);
        } else if ( 580 < wl && wl <= 645 ) {
            red = 1;
        } else if ( 645 < wl && wl <= 780 ) {
            red = 1;
        } else if ( wl > 780 ) {
            red = 1;
        } else if ( wl <= 380 ) {
            red = 1;
        }

        red *= 255;
        return (int)red;
    }

    public int greenValue() {
        float wl = WAVELENGTH * (float)1e9;
        float green = 0;


        //TODO: add intensity corrections for edge of visibility
        if ( 380 < wl && wl <= 440) {
            green = 0;
        } else if ( 440 < wl && wl <= 490 ) {
            green = (wl -440)/(490 - 440);
        } else if ( 490 < wl && wl <= 510 ) {
            green = 1;
        } else if ( 510 < wl && wl <= 580 ) {
            green = 1;
        } else if ( 580 < wl && wl <= 645 ) {
            green = -(wl - 645)/(645 - 580);
        } else if ( 645 < wl && wl <= 780 ) {
            green = 0;
        } else if ( wl > 780 ) {
            green = 0;
        } else if ( wl <= 380 ) {
            green = 0;
        }

        green *= 255;
        return (int)green;
    }

    public int blueValue() {
        float wl = WAVELENGTH * (float)1e9;
        float blue = 0;

       if ( 380 < wl && wl <= 440) {
            blue = 1;
        } else if ( 440 < wl && wl <= 490 ) {
            blue = 1;
        } else if ( 490 < wl && wl <= 510 ) {
            blue = -(wl - 510)/(510 - 490);
        } else if ( 510 < wl && wl <= 580 ) {
            blue = 0;
        } else if ( 580 < wl && wl <= 645 ) {
            blue = 0;
        } else if ( 645 < wl && wl <= 780 ) {
            blue = 0;
        } else if ( wl > 780 ) {
            blue = 0;
        } else if ( wl <= 380 ) {
            blue = 1;
        }

        blue *= 255;
        return (int)blue;
    }

   /* public Color appearance() {
        float wl = WAVELENGTH * (float)1e9;
        float red;
        float green;
        float blue;

        Color output = null;

        //TODO: add intensity corrections for edge of visibility
        if ( 380 < wl && wl < 440) {
            red = -(wl -440)/(440 - 380);
            green = 0;
            blue = 1;
            output = new Color(red, green, blue);
            VISIBLE = true;
        } else if ( 440 < wl && wl < 490 ) {
            red = 0;
            green = (wl -440)/(490 - 440);
            blue = 1;
            output = new Color(red, green, blue);
            VISIBLE = true;
        } else if ( 490 < wl && wl < 510 ) {
            red = 0;
            green = 1;
            blue = -(wl - 510)/(510 - 490);
            output = new Color(red, green, blue);
            VISIBLE = true;
        } else if ( 510 < wl && wl < 580 ) {
            red = (wl - 510)/(580 - 510);
            green = 1;
            blue = 0;
            output = new Color(red, green, blue);
            VISIBLE = true;
        } else if ( 580 < wl && wl < 645 ) {
            red = 1;
            green = -(wl - 645)/(645 - 580);
            blue = 0;
            output = new Color(red, green, blue);
            VISIBLE = true;
        } else if ( 645 < wl && wl < 780 ) {
            red = 1;
            green = 0;
            blue = 0;
            output = new Color(red, green, blue);
            VISIBLE = true;
        } else if ( wl > 780 ) {
            red = 1;
            green = 0;
            blue = 0;
            output = new Color(red, green, blue);
            IR = true;
        } else if ( wl < 380 ) {
            red = 1;
            green = 0;
            blue = 1;
            output = new Color(red, green, blue);
            UV = true;
        }
        return output;
    }*/

    public light SHG() {
        light newLight = new light();
        newLight.setF(2 * this.FREQUENCY);

        return newLight;
    }

    public static light SFG(light ost, light dst) {
        light newLight = new light();
        newLight.setF(ost.FREQUENCY + dst.FREQUENCY);

        return newLight;
    }

    public String spectrumSection() {
        float t = this.WAVELENGTH * (float)1e9;

        if (t <= 760 && t > 380) {
            return "visible";
        } else if (t <= 2500 && t > 760) {
            return "near-infrared";
        } else if (t <= 10000 && t > 2500) {
            return "mid-infrared";
        } else if (t <= 1000000 && t > 10000) {
            return "far-infrared";
        } else if (t <= 1e9 && t > 1000000) {
            return "microwave";
        } else if (t > 1e9) {
            return "radio";
        } else if (t <= 380 && t > 300) {
            return "near-ultraviolet";
        } else if (t <= 300 && t > 200) {
            return "middle-ultraviolet";
        } else if (t <= 200 && t > 10) {
            return "extreme(vacuum)-ultraviolet";
        } else if (t <= 10 && t > 1) {
            return "soft-X-ray";
        } else if (t <= 1 && t > 1e-2) {
            return "hard-X-ray";
        } else if (t <= 1e-2) {
            return "gamma-ray";
        } else return null;

    }

    public static void main(String[] args) {
        light harry = new light(5, UNITS_NM);


        System.out.println(harry.WAVELENGTH);

        System.out.println(harry.FREQUENCY);

        System.out.println(harry.spectrumSection());

        //light Barry = new light();

        /*
        Barry = harry.SHG();
        System.out.println(Barry.WAVELENGTH);

        System.out.println(Barry.FREQUENCY);
        light garry = new light();

        garry = SFG(harry, Barry);
        System.out.println(garry.WAVELENGTH);

        System.out.println(garry.FREQUENCY);*/

    }

}
