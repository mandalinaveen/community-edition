/*
 * Copyright (C) 2005-2007 Alfresco Software Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 * As a special exception to the terms and conditions of version 2.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * and Open Source Software ("FLOSS") applications as described in Alfresco's
 * FLOSS exception.  You should have recieved a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * http://www.alfresco.com/legal/licensing" */
package org.alfresco.filesys.avm;

import org.alfresco.filesys.server.filesys.FileAttribute;
import org.alfresco.filesys.server.filesys.FileInfo;
import org.alfresco.filesys.server.filesys.FileName;
import org.alfresco.filesys.server.filesys.NetworkFile;
import org.alfresco.filesys.server.pseudo.PseudoFile;
import org.alfresco.filesys.server.pseudo.PseudoFolderNetworkFile;

/**
 * Dummy Folder Pseudo File Class
 * 
 * <p>Represents a dummy folder within the virtualization filesystem view.
 *
 * @author gkspencer
 */
public class DummyFolderPseudoFile extends PseudoFile {

	/**
	 * Class constructor
	 * 
	 * @param fname String
	 * @param relPath String
	 */
	public DummyFolderPseudoFile( String fname, String relPath)
	{
		super( fname, FileAttribute.Directory + FileAttribute.ReadOnly);
		
		// Create static file information from the folder details
		
		FileInfo fInfo = new FileInfo( fname, 0L, FileAttribute.Directory + FileAttribute.ReadOnly);
		fInfo.setCreationDateTime( System.currentTimeMillis());
		
		fInfo.setPath( relPath);
		fInfo.setFileId( relPath.hashCode());
		
		long timeNow = System.currentTimeMillis();
		fInfo.setCreationDateTime( timeNow);
		fInfo.setModifyDateTime( timeNow);
		fInfo.setAccessDateTime( timeNow);
		fInfo.setChangeDateTime( timeNow);
		
		setFileInfo( fInfo);
	}
	
    /**
     * Return a network file for reading/writing the pseudo file
     * 
     * @param netPath String
     * @return NetworkFile
     */
	@Override
	public NetworkFile getFile(String netPath) {
		
		// Split the path to get the name
		
		String[] paths = FileName.splitPath( netPath);
		
		// Create a network file for the folder
		
		return new PseudoFolderNetworkFile( paths[1], netPath);
	}

    /**
     * Return the file information for the pseudo file
     *
     * @return FileInfo
     */
	@Override
	public FileInfo getFileInfo() {
		return getInfo();
	}
}
