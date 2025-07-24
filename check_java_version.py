#!/usr/bin/env python3
"""
Python 3.9 script to print the current Java version
"""

import subprocess
import sys

def get_java_version():
    """Get the current Java version using subprocess"""
    try:
        # Run java -version command
        result = subprocess.run(['java', '-version'], 
                              capture_output=True, 
                              text=True, 
                              check=True)
        
        # Java version info is in stderr for some reason
        version_line = result.stderr.split('\n')[0]
        return version_line.strip()
    
    except subprocess.CalledProcessError as e:
        return f"Error running java: {e}"
    except FileNotFoundError:
        return "Java is not installed or not in PATH"
    except Exception as e:
        return f"Unexpected error: {e}"

def main():
    """Main function"""
    print("=== Java Version Checker ===")
    print(f"Python version: {sys.version}")
    print()
    
    java_version = get_java_version()
    print(f"Java version: {java_version}")
    
    # Also try to get JAVA_HOME if available
    try:
        java_home = subprocess.run(['echo', '$JAVA_HOME'], 
                                 capture_output=True, 
                                 text=True, 
                                 shell=True).stdout.strip()
        if java_home and java_home != '$JAVA_HOME':
            print(f"JAVA_HOME: {java_home}")
    except:
        pass

if __name__ == "__main__":
    main()