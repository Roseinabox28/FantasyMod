package thebetweenlands.api.storage;

import java.io.File;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;

public interface ILocalStorageHandler {
	/**
	 * Returns the world storage
	 * 
	 * @return
	 */
	public IWorldStorage getWorldStorage();

	/**
	 * Adds a local storage to the world
	 * 
	 * @param storage
	 * @return
	 */
	public boolean addLocalStorage(ILocalStorage storage);

	/**
	 * Removes a local storage from the world
	 * 
	 * @param storage
	 * @return
	 */
	public boolean removeLocalStorage(ILocalStorage storage);

	/**
	 * Returns the local storage with the specified ID, if currently loaded
	 * 
	 * @param id
	 * @return
	 */
	@Nullable
	public ILocalStorage getLocalStorage(StorageID id);

	/**
	 * Returns a list of all local storages of the specified type at the specified
	 * position
	 * 
	 * @param storageClass
	 * @param selector
	 * @param x
	 * @param z
	 * @return
	 */
	public <T extends ILocalStorage> List<T> getLocalStorages(Class<T> type, double x, double z,
			@Nullable Predicate<T> filter);

	/**
	 * Returns a list of all local storages of the specified type that intersect
	 * with the specified AABB
	 * 
	 * @param aabb
	 * @param type
	 * @return
	 */
	public <T extends ILocalStorage> List<T> getLocalStorages(Class<T> type, AxisAlignedBB aabb,
			@Nullable Predicate<T> filter);

	/**
	 * Deletes the file (or entry if in a region) of the specified local storage
	 * 
	 * @param storage
	 */
	public void deleteLocalStorageFile(ILocalStorage storage);

	/**
	 * Saves the local storage to a file (or entry if in a region)
	 * 
	 * @param storage
	 */
	public void saveLocalStorageFile(ILocalStorage storage);

	/**
	 * Loads the local storage of the specified reference from a file or the region
	 * cache if the local storage uses a region
	 * 
	 * @param reference
	 * @return
	 */
	@Nullable
	public ILocalStorage loadLocalStorage(LocalStorageReference reference);

	/**
	 * Unloads a local storage and saves to a file if necessary
	 * 
	 * @param storage
	 * @return True if the storage was successfully unloaded
	 */
	public boolean unloadLocalStorage(ILocalStorage storage);

	/**
	 * Returns an unmodifiable list of all currently loaded local storages
	 * 
	 * @return
	 */
	public Collection<ILocalStorage> getLoadedStorages();

	/**
	 * Updates all local storages that implement {@link ITickable}
	 */
	public void update();

	/**
	 * Returns the directory where the data of all local storages are saved
	 * 
	 * @return
	 */
	public File getLocalStorageDirectory();

	/**
	 * Creates a local storage instance from the specified NBT, saved by
	 * {@link #saveLocalStorageToNBT(NBTTagCompound, ILocalStorage)}
	 * 
	 * @param nbt
	 * @param region
	 * @param packet
	 * @return
	 */
	public ILocalStorage createLocalStorageFromNBT(NBTTagCompound nbt, LocalRegion region);

	/**
	 * Creates a new local storage
	 * 
	 * @param type
	 * @param id
	 * @param region
	 * @return
	 */
	public ILocalStorage createLocalStorage(ResourceLocation type, StorageID id, @Nullable LocalRegion region);

	/**
	 * Saves a local storage instance to NBT
	 * 
	 * @param nbt
	 * @param storage
	 * @param packet
	 * @return
	 */
	public NBTTagCompound saveLocalStorageToNBT(NBTTagCompound nbt, ILocalStorage storage);

	/**
	 * Saves all local storages and regions
	 */
	public void saveAll();
}
