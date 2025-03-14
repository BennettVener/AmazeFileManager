/*
 * Copyright (C) 2014-2020 Arpit Khurana <arpitkh96@gmail.com>, Vishal Nehra <vishalmeham2@gmail.com>,
 * Emmanuel Messulam<emmanuelbendavid@gmail.com>, Raymond Lai <airwave209gt at gmail.com> and Contributors.
 *
 * This file is part of Amaze File Manager.
 *
 * Amaze File Manager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.amaze.filemanager.database.daos;

import static com.amaze.filemanager.database.UtilitiesDatabase.COLUMN_HOST_PUBKEY;
import static com.amaze.filemanager.database.UtilitiesDatabase.COLUMN_NAME;
import static com.amaze.filemanager.database.UtilitiesDatabase.COLUMN_PATH;
import static com.amaze.filemanager.database.UtilitiesDatabase.COLUMN_PRIVATE_KEY;
import static com.amaze.filemanager.database.UtilitiesDatabase.COLUMN_PRIVATE_KEY_NAME;
import static com.amaze.filemanager.database.UtilitiesDatabase.TABLE_SFTP;

import java.util.List;

import com.amaze.filemanager.database.models.utilities.SftpEntry;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * {@link Dao} interface definition for {@link SftpEntry}. Concrete class is generated by Room
 * during build.
 *
 * @see Dao
 * @see SftpEntry
 * @see com.amaze.filemanager.database.UtilitiesDatabase
 */
@Dao
public interface SftpEntryDao {

  @Insert
  Completable insert(SftpEntry instance);

  @Update
  Completable update(SftpEntry instance);

  @Query("SELECT * FROM " + TABLE_SFTP)
  Single<List<SftpEntry>> list();

  @Query(
      "SELECT * FROM "
          + TABLE_SFTP
          + " WHERE "
          + COLUMN_NAME
          + " = :name AND "
          + COLUMN_PATH
          + " = :path")
  Single<SftpEntry> findByNameAndPath(String name, String path);

  @Query("SELECT * FROM " + TABLE_SFTP + " WHERE " + COLUMN_NAME + " = :name")
  Single<SftpEntry> findByName(String name);

  @Query(
      "SELECT " + COLUMN_HOST_PUBKEY + " FROM " + TABLE_SFTP + " WHERE " + COLUMN_PATH + " = :uri")
  Single<String> getSshHostKey(String uri);

  @Query(
      "SELECT "
          + COLUMN_PRIVATE_KEY_NAME
          + " FROM "
          + TABLE_SFTP
          + " WHERE "
          + COLUMN_PATH
          + " = :uri")
  Single<String> getSshAuthPrivateKeyName(String uri);

  @Query(
      "SELECT " + COLUMN_PRIVATE_KEY + " FROM " + TABLE_SFTP + " WHERE " + COLUMN_PATH + " = :uri")
  Single<String> getSshAuthPrivateKey(String uri);

  @Query("DELETE FROM " + TABLE_SFTP + " WHERE " + COLUMN_NAME + " = :name")
  Completable deleteByName(String name);

  @Query(
      "DELETE FROM "
          + TABLE_SFTP
          + " WHERE "
          + COLUMN_NAME
          + " = :name AND "
          + COLUMN_PATH
          + " = :path")
  Completable deleteByNameAndPath(String name, String path);
}
