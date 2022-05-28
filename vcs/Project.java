package vcs;

public interface Project {
    String getName();

    String getManagerName();
    
    User getProjectManager();
}
