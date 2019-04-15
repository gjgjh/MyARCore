package com.pkunal.myarcore;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import com.google.ar.sceneform.ux.ArFragment;

/**
 * Writing Ar Fragment extends the ArFragment class to include the WRITER_EXTERNAL_STORAGE
 * permission. This adds this permission to the list of permissions presented to the user for
 * granting.
 */
public class WritingArFragment extends ArFragment {
    @Override
    public String[] getAdditionalPermissions() {
        String[] additionalPermissions = super.getAdditionalPermissions();
        int permissionLength = additionalPermissions != null ? additionalPermissions.length : 0;
        String[] permissions = new String[permissionLength + 1];
        permissions[0] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if (permissionLength > 0) {
            System.arraycopy(additionalPermissions, 0, permissions, 1, additionalPermissions.length);
        }
        return permissions;
    }

    public boolean hasWritePermission() {
        return ActivityCompat.checkSelfPermission(
                this.requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    /** Launch Application Setting to grant permissions. */
    public void launchPermissionSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", requireActivity().getPackageName(), null));
        requireActivity().startActivity(intent);
    }
}

