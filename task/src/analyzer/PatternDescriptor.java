package analyzer;

public class PatternDescriptor {
    private String pattern;
    private String fileName;

    public PatternDescriptor(String pattern, String fileType) {
        this.pattern = pattern;
        this.fileName = fileType;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getFileType() {
        return fileName;
    }

    public void setFileType(String fileName) {
        this.fileName = fileName;
    }
}
