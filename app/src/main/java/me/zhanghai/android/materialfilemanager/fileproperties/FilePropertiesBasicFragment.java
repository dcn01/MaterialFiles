/*
 * Copyright (c) 2018 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.materialfilemanager.fileproperties;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.FormatStyle;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialfilemanager.R;
import me.zhanghai.android.materialfilemanager.filesystem.File;
import me.zhanghai.android.materialfilemanager.util.FragmentUtils;
import me.zhanghai.android.materialfilemanager.util.ViewUtils;

public class FilePropertiesBasicFragment extends AppCompatDialogFragment {

    private static final String KEY_PREFIX = FilePropertiesBasicFragment.class.getName() + '.';

    private static final String EXTRA_FILE = KEY_PREFIX + "FILE";

    @BindView(R.id.name)
    TextView mNameText;
    @BindView(R.id.type)
    TextView mTypeText;
    @BindView(R.id.size)
    TextView mSizeText;
    @BindView(R.id.parent_directory)
    TextView mParentDirectoryText;
    @BindView(R.id.last_modification_time)
    TextView mLastModificationTimeText;

    private File mFile;

    /**
     * @deprecated Use {@link #newInstance(File)} instead.
     */
    public FilePropertiesBasicFragment() {}

    public static FilePropertiesBasicFragment newInstance(File file) {
        //noinspection deprecation
        FilePropertiesBasicFragment fragment = new FilePropertiesBasicFragment();
        FragmentUtils.getArgumentsBuilder(fragment)
                .putParcelable(EXTRA_FILE, file);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFile = getArguments().getParcelable(EXTRA_FILE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.file_properties_basic_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mNameText.setText(mFile.getName());
        mTypeText.setText(mFile.getMimeType());
        String size = Formatter.formatFileSize(mSizeText.getContext(), mFile.getSize());
        mSizeText.setText(size);
        // TODO
        //mParentDirectoryText.setText(mFile.getPath());
        String lastModificationTime = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withZone(ZoneId.systemDefault())
                .format(mFile.getLastModificationTime());
        mLastModificationTimeText.setText(lastModificationTime);
    }
}
