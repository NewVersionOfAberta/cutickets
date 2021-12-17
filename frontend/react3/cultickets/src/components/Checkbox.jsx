import React from "react";

export const Checkbox = ({children}) => {
    const [checked, setChecked] = React.useState(true);
    const {fetchAllShows, fetchSuitableShows} = children;
    const handler = () => {
        if (checked) {
            fetchAllShows();
        } else {
            fetchSuitableShows();
        }
        setChecked(!checked);
    }
    return <label>
    <input type="checkbox" checked={checked} onChange={() => handler()}/>
    See for your region
  </label>
}