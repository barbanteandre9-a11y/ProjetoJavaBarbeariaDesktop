package entities;

public class Service {
    
    private long id;
    private String name;
    private String description;
    private int price;
    private int durationMinutes;
    private boolean isActive; 
    public Service() {}

    public long getId() { 
        return id; 
    }

    public String getName() { 
        return name; 
    }

    public String getDescription() { 
        return description; 
    }

    public int getPrice() { 
        return price; 
    }

    public int getDurationMinutes() { 
        return durationMinutes; 
    }

    public boolean isActive() { 
        return isActive; 
    }

    public void setId(long id) { 
        this.id = id; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public void setDescription(String description) { 
        this.description = description; 
    }

    public void setPrice(int price) { 
        this.price = price; 
    }

    public void setDurationMinutes(int durationMinutes) { 
        this.durationMinutes = durationMinutes; 
    }

    public void setActive(boolean active) { 
        isActive = active; 
    }
}