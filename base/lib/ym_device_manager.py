from multiprocessing import Manager
ym_devices_map = None
ym_devices = None


# cross process
def init_manager():
    manager = Manager()
    ym_devices = manager.list([])

def get_free_device_node():
    pass

def is_all_busy():
    pass

def insert_client_to_list(client_socket):
    # insert available node to list
    ym_devices.append(client_socket)
    
    
if __name__ == "__main__":
    init_manager()