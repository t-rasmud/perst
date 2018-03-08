package org.garret.perst.impl;
import  org.garret.perst.*;

import java.lang.reflect.*;
import java.io.*;

public class OSFile implements IFile { 
    public void write(long pos, byte[] buf) 
    {
        try { 
            file.seek(pos);
            file.write(buf, 0, buf.length);
        } catch(IOException x) { 
            throw new StorageError(StorageError.FILE_ACCESS_ERROR, x);
        }
    }

    public int read(long pos, byte[] buf) 
    { 
        try { 
            file.seek(pos);
            return file.read(buf, 0, buf.length);
        } catch(IOException x) { 
            throw new StorageError(StorageError.FILE_ACCESS_ERROR, x);
        }
    }
        
    public void sync()
    { 
        if (!noFlush) { 
            try {   
                file.getFD().sync();
            } catch(IOException x) { 
                throw new StorageError(StorageError.FILE_ACCESS_ERROR, x);
            }
        }
    }
    
    public void close() 
    { 
        try { 
            file.close();
        } catch(IOException x) { 
            throw new StorageError(StorageError.FILE_ACCESS_ERROR, x);
        }
    }

    public boolean tryLock(boolean shared) 
    { 
        return true;
    }

    public void lock(boolean shared) 
    { 
    }

    public void unlock() 
    { 
    }

    public OSFile(String filePath, boolean readOnly, boolean noFlush) { 
        this.noFlush = noFlush;
        try { 
            file = new RandomAccessFile(filePath, readOnly ? "r" : "rw");
        } catch(IOException x) { 
            throw new StorageError(StorageError.FILE_ACCESS_ERROR, x);
        }
    }

    public long length() {
        try { 
            return file.length();
        } catch (IOException x) { 
            return -1;
        }
    }


    protected RandomAccessFile file;
    protected boolean          noFlush;
}
