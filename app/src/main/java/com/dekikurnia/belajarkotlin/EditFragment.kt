package com.dekikurnia.belajarkotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.dekikurnia.belajarkotlin.models.Crud
import io.realm.Realm
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.find
import java.util.*

/**
 * Created by server02 on 05/06/2017.
 */
class EditFragment : Fragment() {

    val CRUD_ID_KEY: String = "crud_id_key"

    val realm: Realm = Realm.getDefaultInstance()

    var crud: Crud? = null

    companion object {
        fun newInstance(id: String): EditFragment {
            var args: Bundle = Bundle()
            args.putString("crud_id_key", id)
            var editFragment: EditFragment = newInstance()
            editFragment.arguments = args
            return editFragment
        }

        fun newInstance(): EditFragment {
            return EditFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            verticalLayout {
                padding = dip(30)
                var title = editText {
                    id = R.id.crud_title
                    hintResource = R.string.title_hint
                }

                var desc = editText {
                    id = R.id.crud_desc
                    hintResource = R.string.description_hint
                }
                button {
                    id = R.id.crud_add
                    textResource = R.string.add_item
                    onClick { view -> createTodoFrom(title, desc) }
                }
            }
        }.view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        if(arguments != null && arguments.containsKey(CRUD_ID_KEY)) {
            val crudId = arguments.getString(CRUD_ID_KEY)
            crud = realm.where(Crud::class.java).equalTo("id", crudId).findFirst()
            val todoTitle = find<EditText>(R.id.crud_title)
            todoTitle.setText(crud?.title)
            val todoDesc = find<EditText>(R.id.crud_desc)
            todoDesc.setText(crud?.description)
            val add = find<Button>(R.id.crud_add)
            add.setText(R.string.save)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }



    /**
     *  A private function to create a TODO item in the database (Realm).
     *
     *  @param title the title edit text.
     *  @param desc the description edit text.
     */
    private fun createTodoFrom(title: EditText, desc: EditText) {
        realm.beginTransaction()

        // Either update the edited object or create a new one.
        var t = crud?: realm.createObject(Crud::class.java)
        t.id = crud?.id?: UUID.randomUUID().toString()
        t.title = title.text.toString()
        t.description = desc.text.toString()
        realm.commitTransaction()

        // Go back to previous activity
        activity.supportFragmentManager.popBackStack();
    }


}