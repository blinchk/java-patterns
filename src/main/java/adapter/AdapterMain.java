package adapter;

import java.util.Objects;

public class AdapterMain {
    public static void main(String[] args) {
        Resolution resolution = new Resolution(1920, 1080);
        int frequency = 60;
        Image image = new Image(resolution, frequency);
        ThunderboltCable thunderboltCable = new SimpleThunderboltCable(image);
        HdmiCable hdmiCable = new ThunderboltHdmiAdapter(thunderboltCable);
        Display display = new Display();
        Image receivedImage = display.getImage(hdmiCable);
        boolean isEqual = Objects.equals(receivedImage, image);
        System.out.println(isEqual);
    }
}

class Resolution {
    private final int width;
    private final int height;

    Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }
}

class Image {
    private final Resolution resolution;
    private final int frequency;
    public Image(Resolution resolution, int frequency) {
        this.resolution = resolution;
        this.frequency = frequency;
    }
}

interface ThunderboltCable {
    Image getImage();
}

interface HdmiCable {
    Image getImage();
}

class SimpleThunderboltCable implements ThunderboltCable {
    private final Image image;

    public SimpleThunderboltCable(Image image) {
        this.image = image;
    }

    @Override
    public Image getImage() {
        return this.image;
    }
}

class ThunderboltHdmiAdapter implements HdmiCable {
    private final ThunderboltCable thunderboltCable;

    public ThunderboltHdmiAdapter(ThunderboltCable thunderboltCable) {
        this.thunderboltCable = thunderboltCable;
    }

    @Override
    public Image getImage() {
        return thunderboltCable.getImage();
    }
}

class Display {
    public Image getImage(HdmiCable cable) {
        return cable.getImage();
    }
}
